package com.woniu.fitness.service;

import com.woniu.fitness.model.User;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/18
 * @since 1.0.0
 */
public interface IUserService {
    List<User> findAll(String message, int info);

    User findOneByAccount(String account);

    int addUser(User user);

    /*拉黑*/
    int defriend(int id);

    /*还原*/
    int restore(int id);

    int update(User user);

    int updatePassword(User user);
}
