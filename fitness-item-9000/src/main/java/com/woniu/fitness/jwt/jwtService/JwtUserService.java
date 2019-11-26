package com.woniu.fitness.jwt.jwtService;

import com.woniu.fitness.mapper.JwtUserMapper;
import com.woniu.fitness.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinbin
 * @date 2018-07-08 20:52
 */
@Service("UserService")
public class JwtUserService {
    @Autowired
    JwtUserMapper jwtUserMapper;
    public User findByUsername(User user){

        return jwtUserMapper.selectOneByAccount(user.getAccount());
    }
    public User findUserById(String userId) {

        return jwtUserMapper.selectOneByUserID(userId);
    }

}
