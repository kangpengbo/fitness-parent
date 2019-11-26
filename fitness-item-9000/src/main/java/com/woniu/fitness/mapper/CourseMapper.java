package com.woniu.fitness.mapper;

import com.woniu.fitness.model.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CourseMapper {
    @Select("select * from t_course")
    List<Course> selectAll();
    @Select("select * from t_course where course_id=#{id}")
    Course selectById(int id);
    @Insert("insert into t_course (course_name,introduction,course_time,fat_burning,difficulty,instrument,video,crowd,is_pay,type_id) " +
            "values (#{course_name},#{introduction},#{course_time},#{fat_burning},#{difficulty},#{instrument},#{video}," +
            "#{crowd},#{is_pay},#{type_id})")
    void insert(Course course);
    @Update("update t_course set course_state=3 where course_id=#{id} ")
    void deleteById(Integer id);
    @Update("update t_course set course_name=#{course_name},introduction=#{introduction},course_time=#{course_time},fat_burning=#{fat_burning}," +
            "difficulty=#{difficulty},instrument=#{instrument},video=#{video},crowd=#{crowd},is_pay=#{is_pay},type_id=#{type_id},course_state=1" +
            " where course_id=#{course_id}")
    void updateById(Course course);
    @Select("select * from t_course where course_id=#{id}")
    Course selectOneById(Integer id);
    //根据课程名称做模糊查询
    List<Course> selectByName(String name);

    @Update("update t_course set course_state=3 where type_id=#{id} ")
    void deleteByTypeId(Integer id);
}
