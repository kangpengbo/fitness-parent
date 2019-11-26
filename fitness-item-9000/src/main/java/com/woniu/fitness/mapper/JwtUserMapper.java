package com.woniu.fitness.mapper;

import com.woniu.fitness.model.User;
import org.apache.ibatis.annotations.Select;


public interface JwtUserMapper {
    @Select("select * from t_user where account = #{value}")
    User selectOneByAccount(String account);

    @Select("select * from t_user where user_id = #{id}")
    User selectOneByUserID(String id);
}
