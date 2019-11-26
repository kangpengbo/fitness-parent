package com.woniu.fitness.mapper;

import com.woniu.fitness.model.CourseOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface CourseOrderMapper {

    //生成订单
    int insert(CourseOrder courseOrder);

    //增加用户课程中间表
    @Insert("insert into t_user_course (user_id,course_id) values (#{user_id},#{course_id})")
    int insertUserCourse(@Param("user_id") int user_id,@Param("course_id") int course_id);

    //用户查询自己的订单
    @Select("select * from t_course_order where user_id=#{id}")
    List<CourseOrder> selectByUser_id(int user_id);

    //用户删除订单，修改订单状态
    @Update("update t_course_order set state=0,change_time=#{change_time} where order_number=#{order_number}")
    int remove(@Param("order_number") String order_number, @Param("change_time") Date change_time);
}
