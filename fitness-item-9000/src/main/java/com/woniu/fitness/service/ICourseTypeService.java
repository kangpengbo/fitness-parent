package com.woniu.fitness.service;

import com.woniu.fitness.model.CourseType;

import java.util.List;

public interface ICourseTypeService {
    List<CourseType> findAll();

    void addOne(CourseType courseType);

    void removeOne(int id);

    void modifyOne(CourseType courseType);
}
