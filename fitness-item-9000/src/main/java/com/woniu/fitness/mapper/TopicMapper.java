package com.woniu.fitness.mapper;

import com.woniu.fitness.model.Topic;
import org.apache.ibatis.annotations.*;
import java.util.List;


public interface TopicMapper {

    //查询所有话题
    List<Topic> selectAll(@Param("message") String message);

    //添加话题
    @Insert("insert into t_topic (topic_title,topic_content,create_time) values (#{topic_title},#{topic_content},#{create_time})")
    int insert(Topic topic);

    //删除话题
    @Update("update t_topic set topic_state=0 where topic_id=#{id}")
    int remove(int id);

    //复原话题
    @Update("update t_topic set topic_state=1 where topic_id=#{id}")
    int recover(int id);

    //修改话题浏览数
    @Update("update t_topic set topic_views=topic_views+1 where topic_id=#{id}")
    int updateView(int id);

    //修改话题点赞数
    @Update("update t_topic set topic_likes=topic_likes+1 where topic_id=#{id}")
    int updateLikes(int id);

    @Delete("delete from t_topic where topic_id=#{id}")
    int delete(int id);

}
