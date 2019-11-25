package com.woniu.fitness.mapper;

import com.woniu.fitness.model.CourseType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CourseTypeMapper {
    @Select("select * from t_course_type")
    List<CourseType> selectAll();
    @Insert("insert into t_course_type (type_name) values (#{type_name})")
    void insertOne(CourseType courseType);
    @Delete("delete from t_course_type where type_id=#{type_id}")
    void deleteOne(Integer type_id);
    @Update("update t_course_type set type_name=#{type_name} where type_id=#{type_id}")
    void updateOne(CourseType courseType);
}
