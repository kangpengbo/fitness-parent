package com.woniu.fitness.service;

import com.woniu.fitness.mapper.CommentMapper;
import com.woniu.fitness.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int add(Comment comment) {
        comment.setCreate_time(new Date());
        return commentMapper.insert(comment);
    }

    @Override
    @Transient
    public List<Comment> findAll(int dynamic_id) {
        return commentMapper.selectAll(dynamic_id);
    }

    //删除评论
    @Override
    public int delete(Comment comment, StringBuilder arr) {
        //删除该评论下面所有的评论,判断主评论是否存在
        if (!arr.equals(")") && commentMapper.delete(comment.getUser_id(), comment.getDynamic_id(), comment.getComment_id()) != 0) {
            return commentMapper.deleteReply(arr);
        }
        return 0;
    }

    @Override
    public List<Integer> findReplyById(int id) {
        return commentMapper.findReplyById(id);
    }

    @Override
    public List<Integer> findAllReply(int id, List<Integer> arr) {
        List<Integer> list = findReplyById(id);
        arr.addAll(list);
        if (list != null) {
            for (Integer i : list) {
                findAllReply(i, arr);
            }
        }
        return list;
    }
}
