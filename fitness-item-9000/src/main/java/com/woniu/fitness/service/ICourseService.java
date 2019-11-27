package com.woniu.fitness.service;

import com.woniu.fitness.model.Course;

import java.util.List;

public interface ICourseService {
    List<Course> findAll();
    Course findById(int id);
    //模糊查询
    List<Course> findByName(String name);
    Course findOne(Integer id);
    void removeOne(Integer id);
    void addOne(Course course);
    void modifyOne(Course course);
    List<Course> findByUserId(int id);
}
