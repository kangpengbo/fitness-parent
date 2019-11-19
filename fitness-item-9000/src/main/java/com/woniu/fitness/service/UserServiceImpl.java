package com.woniu.fitness.service;

import com.woniu.fitness.mapper.UserMapper;
import com.woniu.fitness.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public User findOneByAccount(String account) {
        return userMapper.selectOneByAccount(account);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }
}
