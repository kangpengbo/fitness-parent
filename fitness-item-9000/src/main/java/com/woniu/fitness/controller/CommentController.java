package com.woniu.fitness.controller;

import com.woniu.fitness.model.Comment;
import com.woniu.fitness.response.ResponseResult;
import com.woniu.fitness.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private ICommentService service;

    //查询所有评论
    @RequestMapping("/list")
    public ResponseResult findAll(int dynamic_id) {
        List<Comment> list = service.findAll(dynamic_id);
        Map<String, Object> map = new HashMap<>();
        map.put("commentlist", list);
        return new ResponseResult("200", "查询成功!").setMap(map);
    }

    //添加和回复评论评论
    @RequestMapping("/add")
    public ResponseResult addComment(@RequestBody Comment comment) {
        service.add(comment);
        return new ResponseResult("200", "回复成功!");
    }

    //删除评论
    @RequestMapping("/delete")
    public ResponseResult delete(@RequestBody Comment comment) {
        List<Integer> list = new ArrayList<>();
        service.findAllReply(comment.getComment_id(), list);
        StringBuilder arr = new StringBuilder();
        arr.append("(");
        for (Integer i : list) {
            arr.append(i + ",");
        }
        arr.append(")");
        arr.deleteCharAt(arr.length() - 2);
        if (service.delete(comment, arr) != 0) {
            return new ResponseResult("200", "删除成功!");
        } else {
            return new ResponseResult("200", "你无权限删除该评论!");
        }
    }

}
