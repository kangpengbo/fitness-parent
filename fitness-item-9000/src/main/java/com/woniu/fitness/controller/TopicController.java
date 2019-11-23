package com.woniu.fitness.controller;

import com.woniu.fitness.model.Topic;
import com.woniu.fitness.response.ResponseResult;
import com.woniu.fitness.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("topic")
public class TopicController {
    @Autowired
    private ITopicService topicService;

    //展示所有话题
    @RequestMapping("/list")
    public ResponseResult list(@RequestParam(defaultValue = "") String message) {
        List<Topic> list = topicService.findAll(message);
        Map<String, Object> map = new HashMap<>();
        map.put("topiclist", list);
        return new ResponseResult("200", "查询成功!").setMap(map);
    }

    //浏览数+1
    @RequestMapping("/view")
    public ResponseResult addview(int id) {
        topicService.updateView(id);
        return new ResponseResult("200", "成功!");
    }

    //点赞数+1
    @RequestMapping("/addlike")
    public ResponseResult addlike(int id) {
        topicService.addLikes(id);
        return new ResponseResult("200", "成功!");
    }

    //取消赞
    @RequestMapping("/substractlike")
    public ResponseResult substractlike(int id) {
        topicService.substractLikes(id);
        return new ResponseResult("200", "已取消!");
    }

    //添加话题
    @RequestMapping("/add")
    public ResponseResult addTopic(@RequestBody Topic topic) {
        topicService.add(topic);
        return new ResponseResult("200", "添加成功!");
    }

    //删除话题，修改话题状态
    @RequestMapping("/remove")
    public ResponseResult removeTopic(int id, int state) {
        if (state == 1) {
            topicService.remove(id);
            return new ResponseResult("200", "操作成功!");
        } else {
            topicService.recover(id);
            return new ResponseResult("200", "复原成功!");
        }
    }

    //永久删除话题
    @RequestMapping("/delete")
    public ResponseResult deleteTopic(int id) {
        topicService.delete(id);
        return new ResponseResult("200", "删除成功!");
    }
}
