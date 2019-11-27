package com.woniu.fitness.service;

import com.woniu.fitness.mapper.UserMapper;
import com.woniu.fitness.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/18
 * @since 1.0.0
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transient
    public List<User> findAll(String message, int info) {
        return userMapper.selectAll(message, info);
    }

    @Override
    public User findOneByAccount(String account) {
        return userMapper.selectOneByAccount(account);
    }

    @Override
    public int addUser(User user) {
        user.setCreate_time(new Date());
        return userMapper.insert(user);
    }

    @Override
    public int defriend(int id) {
        return userMapper.defriend(id);
    }

    @Override
    public int restore(int id) {
        return userMapper.restore(id);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int updatePassword(User user) {
        return userMapper.updatePassword(user);
    }


    //根据用户名查询用户
    @Override
    public User findByAccount(String account) {
        return userMapper.selectByAccount(account);
    }

    @Override
    public List<User> findAllFans(int user_id) {
        return userMapper.selectAllFans(user_id);
    }

    @Override
    public List<User> findAllAttention(int user_id) {
        return userMapper.selectAllAttention(user_id);
    }

    @Override
    public int addAttention(int user_id, int fan_id) {
        return userMapper.addAttention(user_id, fan_id);
    }

    @Override
    public int removeAttention(int user_id, int fan_id) {
        return userMapper.removeAttention(user_id, fan_id);
    }

    @Override
    public int addMoney(int user_id, double money) {
        return userMapper.addMoney(user_id, money);
    }

    @Override
    public int consume(int user_id, double money) {
        return userMapper.consume(user_id, money);
    }
}
