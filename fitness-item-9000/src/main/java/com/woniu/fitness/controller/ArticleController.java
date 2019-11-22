package com.woniu.fitness.controller;

import com.woniu.fitness.model.Article;
import com.woniu.fitness.response.ResponseResult;
import com.woniu.fitness.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    //得出所有的文章
    @RequestMapping("/list")
    public ResponseResult list(@RequestParam(defaultValue = "") String message) {
        List<Article> list = articleService.findAll(message);
        Map<String, Object> map = new HashMap<>();
        map.put("articleList", list);
        return new ResponseResult("200", "查询成功!").setMap(map);
    }

    //根据id查询单个文章
    @RequestMapping("/findOne")
    public ResponseResult findOne(int id) {
        Article article = articleService.findById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("article", article);
        return new ResponseResult("200", "查询成功!").setMap(map);
    }

    //删除文章
    @RequestMapping("/delete")
    public ResponseResult delete(int id) {
        articleService.delete(id);
        return new ResponseResult("200", "删除成功!");
    }

    //操作文章状态
    @RequestMapping("/operate")
    public ResponseResult operate(int id, int state) {
        articleService.operate(id, state);
        return new ResponseResult("200", "操作成功!");
    }

    //修改文章
    @RequestMapping("/update")
    public ResponseResult update(@RequestBody Article article) {
        articleService.update(article);
        return new ResponseResult("200", "修改成功!");
    }

    //添加文章
    @RequestMapping("/add")
    public ResponseResult addArticle(@RequestBody Article article) {
        articleService.add(article);
        return new ResponseResult("200", "添加成功!");
    }

    //点赞
    @RequestMapping("/addlike")
    public ResponseResult addlike(int id) {
        articleService.addLike(id);
        return new ResponseResult("200", "点赞成功!");
    }

    //取消赞
    @RequestMapping("/subtractlike")
    public ResponseResult subtractlike(int id) {
        articleService.subtractLike(id);
        return new ResponseResult("200", "撤赞成功!");
    }

    //浏览+1
    @RequestMapping("/addView")
    public ResponseResult addView(int id) {
        articleService.addView(id);
        return new ResponseResult("200","");
    }
}
