package com.woniu.fitness.mapper;

import com.woniu.fitness.model.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    //添加评论
    int insert(Comment comment);

    //查询所有评论
    List<Comment> selectAll(int dynamic_id);

    //删除评论
    @Delete("delete from t_comment where user_id=#{user_id} and dynamic_id=#{dynamic_id} and comment_id=#{comment_id}")
    int delete(@Param("user_id") int user_id, @Param("dynamic_id") int dynamic_id, @Param("comment_id") int comment_id);

    //删除评论下面的回复
    int deleteReply(@Param("arr") StringBuilder arr);

    //查询父类下面的子类
    List<Integer> findReplyById(int id);
}
