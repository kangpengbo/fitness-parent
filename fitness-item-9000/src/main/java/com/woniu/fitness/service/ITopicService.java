package com.woniu.fitness.service;

import com.woniu.fitness.model.Topic;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ITopicService {

    //查询所有话题
    List<Topic> findAll(String message);

    //添加话题
    int add(Topic topic);

    //删除话题
    int remove(int id);

    //复原话题
    int recover(int id);

    //修改话题浏览数
    int updateView(int id);

    //修改话题点赞数
    int updateLikes(int id);

    //永久删除话题
    int delete(int id);
}
