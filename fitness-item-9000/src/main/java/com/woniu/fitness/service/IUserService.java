package com.woniu.fitness.service;

import com.woniu.fitness.model.User;
import org.apache.ibatis.annotations.Param;

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

    //根据用户名查询用户
    User findByAccount(String account);

    //根据user_id查询出所有的粉丝
    List<User> findAllFans(int user_id);

    //根据user_id查询出所有已关注的用户
    List<User> findAllAttention(int user_id);

    //添加关注
    int addAttention(int user_id, int fan_id);

    //取消关注
    int removeAttention(int user_id, int fan_id);
}
