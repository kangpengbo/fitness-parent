package com.woniu.fitness.mapper;

import com.woniu.fitness.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAll();

    @Select("select * from t_user where account = #{value}")
    User selectOneByAccount(String account);

    @Insert("insert into t_user (account,password,nickname,sex,birthday,telephone,email,head_image,height,weight,city,introduction) values (#{account},#{password},#{nickname},#{sex},#{birthday},#{telephone},#{email},#{head_image},#{height},#{weight},#{city},#{introduction})")
    int insert(User user);
}
