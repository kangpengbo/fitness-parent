package com.woniu.fitness.mapper;

import com.woniu.fitness.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /*条件查询用户信息*/
    List<User> selectAll(@Param("message") String message, @Param("info") int info);

    @Select("select * from t_user where account = #{value}")
    User selectOneByAccount(String account);

    //注册添加用户
    @Insert("insert into t_user (account,password,nickname,sex,birthday,telephone,email,create_time) values (#{account},#{password},#{nickname},#{sex},#{birthday},#{telephone},#{email},#{create_time})")
    int insert(User user);

    /*拉黑*/
    @Update("update t_user set state=0 where user_id=#{id}")
    int defriend(int id);

    /*还原*/
    @Update("update t_user set state=1 where user_id=#{id}")
    int restore(int id);

    /*修改用户*/
    @Update("update t_user set nickname=#{nickname},telephone=#{telephone},email=#{email},head_image=#{head_image},height=#{height},weight=#{weight},city=#{city},introduction=#{introduction}")
    int update(User user);

    /*修改密码*/
    @Update("update t_user set password=#{value}")
    int updatePassword(String password);
}
