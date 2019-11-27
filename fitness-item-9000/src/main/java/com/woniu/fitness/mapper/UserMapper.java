package com.woniu.fitness.mapper;

import com.woniu.fitness.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    /*条件查询用户信息*/
    List<User> selectAll(@Param("message") String message, @Param("info") int info);

    @Select("select * from t_user where account = #{value}")
    User selectOneByAccount(String account);

    //注册添加用户
    @Insert("insert into t_user (account,password,nickname,sex,birthday,telephone,email,create_time) values (#{account},#{password},#{nickname},#{sex},#{birthday},#{telephone},#{email},#{create_time})")
    int insert(User user);

    //给用户充值
    @Update("update t_user set money=money + ${money} where user_id=#{user_id}")
    int addMoney(@Param("user_id") int user_id, @Param("money") double money);

    //消费金额
    @Update("update t_user set money=money - ${money} where user_id=#{user_id}")
    int consume(@Param("user_id") int user_id, @Param("money") double money);

    /*拉黑*/
    @Update("update t_user set state=0 where user_id=#{id}")
    int defriend(int id);

    /*还原*/
    @Update("update t_user set state=1 where user_id=#{id}")
    int restore(int id);

    /*修改用户*/
    @Update("update t_user set nickname=#{nickname},telephone=#{telephone},email=#{email},head_image=#{head_image},height=#{height},weight=#{weight},city=#{city},introduction=#{introduction} where user_id=#{user_id}")
    int update(User user);

    /*修改密码*/
    @Update("update t_user set password=#{password} where user_id=#{user_id}")
    int updatePassword(User user);

    //根据用户名查询
    @Select("select * from t_user where account=#{value}")
    User selectByAccount(String account);

    //根据user_id查询出所有的粉丝
    List<User> selectAllFans(int user_id);

    //根据user_id查询出所有已关注的用户
    List<User> selectAllAttention(int user_id);

    //添加关注
    @Insert("insert into t_user_fan (user_id,fan_id) values (#{user_id},#{fan_id})")
    int addAttention(@Param("user_id") int user_id, @Param("fan_id") int fan_id);

    //取消关注
    @Delete("delete from t_user_fan where user_id=#{user_id} and fan_id=#{fan_id}")
    int removeAttention(@Param("user_id") int user_id, @Param("fan_id") int fan_id);

    //根据user_id查询用户
    @Select("select * from t_user where user_id=#{id}")
    User selectById(int id);
}
