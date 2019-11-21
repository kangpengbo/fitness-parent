package com.woniu.fitness.service;

import com.woniu.fitness.model.Comment;

import java.util.List;

public interface ICommentService {
    //添加评论
    int add(Comment comment);

    //查询所有评论
    List<Comment> findAll(int dynamic_id);

    //删除某个评论
    int delete(Comment comment, StringBuilder arr);

    List<Integer> findReplyById(int id);

    public List<Integer> findAllReply(int id, List<Integer> arr) ;
}
