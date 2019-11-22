package com.woniu.fitness.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.fitness.model.Comment;
import com.woniu.fitness.model.Dynamic;
import com.woniu.fitness.service.ICommentService;
import com.woniu.fitness.view.RedisUtil;
import com.woniu.fitness.response.ResponseResult;
import com.woniu.fitness.service.IDynamicService;
import com.woniu.fitness.utils.FileUrlUtil;
import com.woniu.fitness.vo.DynamicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author 11718
 * @create 2019/11/19
 * @since 1.0.0
 */
@RestController
@RequestMapping("dynamic")
public class DynamicController {
    @Autowired
    private IDynamicService dynamicService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private RedisUtil redisUtil;

    //返回值
    private final static ResponseResult FALSE=new ResponseResult("500","操作有误！");
    private final static ResponseResult TRUE=new ResponseResult("200","操作成功！");

    //添加动态(不能同时发视频和图片，只选其一)
    @PostMapping("/add")
    public ResponseResult addDynamic(@RequestBody Dynamic dynamic){
        //内容不空，不长于140个字，
        if(dynamic==null || dynamic.getDynamic_content().length()>140){
            return FALSE;
        }
        //用户信息
        dynamic.setUser_id(1); //测试,后期改
        dynamic.setUser_account("jack"); //测试,后期改
        //时间
        dynamic.setCreate_time(new Date());
        //当文件地址不为空，判断地址是否有效,即文件是否存在
        if(dynamic.getDynamic_image()!=null){
            String isFile= FileUrlUtil.testUrl(dynamic.getDynamic_image());
            System.out.println(isFile);
            if("文件存在".equals(isFile)){
                //若文件存在，判断文件类型
                String format=FileUrlUtil.judgeFile(dynamic.getDynamic_image());
                System.out.println(format);
                 if("视频".equals(format)){
                    dynamic.setDynamic_video(dynamic.getDynamic_image());
                    dynamic.setDynamic_image(null);
                    dynamic.setState(2);
                    dynamicService.addDynamic(dynamic);
                    return TRUE;
                }else if("图片".equals(format)){ //图片
                    dynamic.setState(1);
                    dynamicService.addDynamic(dynamic);
                    return TRUE;
                }
            }
            return FALSE; //文件不存在或类型错误
        }
        //纯文字动态
        dynamic.setState(0);
        dynamicService.addDynamic(dynamic);
        return TRUE;
    }

    //删除动态
    @DeleteMapping("/delete")
    public ResponseResult deleteDynamic(Integer id){
        System.out.println(id);
        //动态是否存在，请求是否来自该用户
        Dynamic dynamic=dynamicService.findOneDynamic(id);
        if(dynamic!=null && dynamic.getUser_id()==1){ //此处1为用户id,后期需修改
            dynamicService.removeDynamic(id);
            return TRUE;
        }
        return FALSE;
    }

    //查询我的所有动态
    @GetMapping("/list")
    public ResponseResult getMyAllDynamic(){
        List<Dynamic> dynamics=dynamicService.findAllDynamic(1); //测试user_id,后期改
        return TRUE.add("dynamics",dynamics);
    }

    //分页查询广场所有动态
    @GetMapping("/pageList")
    public ResponseResult getAllDynamic(@RequestParam(value = "pageNow",defaultValue = "1")Integer pageNow){
        PageHelper.startPage(pageNow,3); //每页3条，可修改
        List<Dynamic> dynamics=dynamicService.findAllNOCondition();
        PageInfo<Dynamic> pageInfo=new PageInfo(dynamics,3);
        return TRUE.add("pageInfo",pageInfo);
    }

    //动态详情
    @GetMapping("/detail")
    public ResponseResult getDetailAndComment(Integer id){
        //空参
        if(id==null){
            return FALSE;
        }
        //详情
        Dynamic dynamic=dynamicService.findOneDynamic(id);
        //空数据
        if(dynamic==null){
            return FALSE;
        }
        //key
        String key="dynamic:"+dynamic.getDynamic_id();
        Integer value=null;
        if(!redisUtil.hasKey(key)){ //第一次浏览
           redisUtil.add(key,"1");
            value=1;
        }else{ //不是第一次，自增1
            value= Math.toIntExact(redisUtil.incr(key));
        }
        //浏览量
        dynamic.setDynamic_views(value);
        //评论
        List<Comment> comments=commentService.findAll(id);
        return TRUE.add("dynamic",dynamic).add("comments",comments);
    }

    //点赞加1
    public ResponseResult incrlikes(Integer id){

        return null;
    }

    //======================分割线，以下是后台管理接口不用看=============================================================


    //动态列表，搜索
    @GetMapping("/all")
    public ResponseResult getAll(DynamicVo vo){
        System.out.println(vo);
        Date times=new Date();
        System.out.println(times);
        List<Dynamic> dynamics=dynamicService.findAll(vo);
        return TRUE.add("dynamics",dynamics);
    }

    //删除
    @DeleteMapping("/del")
    public ResponseResult deleteById(Integer id){
        //动态是否存在
        Dynamic dynamic=dynamicService.findOneDynamic(id);
        if(dynamic!=null){
            dynamicService.removeDynamic(id);
            return TRUE;
        }
        return FALSE;
    }

}