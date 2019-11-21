package com.woniu.fitness.service;

import com.woniu.fitness.mapper.TopicMapper;
import com.woniu.fitness.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TopicServiceImpl implements ITopicService {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    @Transient
    public List<Topic> findAll(String message) {
        return topicMapper.selectAll(message);
    }

    @Override
    public int add(Topic topic) {
        topic.setCreate_time(new Date());
        return topicMapper.insert(topic);
    }

    @Override
    public int remove(int id) {
        return topicMapper.remove(id);
    }

    @Override
    public int recover(int id) {
        return topicMapper.recover(id);
    }

    @Override
    public int updateView(int id) {
        return topicMapper.updateView(id);
    }

    @Override
    public int updateLikes(int id) {
        return topicMapper.updateLikes(id);
    }

    @Override
    public int delete(int id) {
        return topicMapper.delete(id);
    }
}
