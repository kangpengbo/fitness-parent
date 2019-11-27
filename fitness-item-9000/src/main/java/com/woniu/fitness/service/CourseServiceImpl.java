package com.woniu.fitness.service;

import com.woniu.fitness.mapper.CourseMapper;
import com.woniu.fitness.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/19
 * @since 1.0.0
 */
@Service
@Transactional
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> findAll() {
        return courseMapper.selectAll();
    }

    @Override
    public Course findById(int id) {
        return courseMapper.selectById(id);
    }

    @Override
    public List<Course> findByName(String name) {
        return courseMapper.selectByName(name);
    }

    @Override
    public Course findOne(Integer id) {
        return courseMapper.selectOneById(id);
    }

    @Override
    public void removeOne(Integer id) {
        courseMapper.deleteById(id);
    }

    @Override
    public void addOne(Course course) {
        courseMapper.insert(course);
    }

    @Override
    public void modifyOne(Course course) {
        courseMapper.updateById(course);
    }

    @Override
    public List<Course> findByUserId(int id) {
        return courseMapper.selectByUserId(id);
    }
}