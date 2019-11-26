package com.woniu.fitness.service;

import com.woniu.fitness.mapper.CourseMapper;
import com.woniu.fitness.mapper.CourseTypeMapper;
import com.woniu.fitness.model.CourseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/21
 * @since 1.0.0
 */
@Service
@Transactional
public class CourseTypeServiceImpl implements ICourseTypeService {
    @Autowired
    CourseTypeMapper courseTypeMapper;
    @Autowired
    CourseMapper courseMapper;
    @Override
    public List<CourseType> findAll() {
        return courseTypeMapper.selectAll();
    }

    @Override
    public void addOne(CourseType courseType) {
        courseTypeMapper.insertOne(courseType);
    }

    @Override
    public void removeOne(int id) {

        courseTypeMapper.deleteOne(id);
        courseMapper.deleteByTypeId(id);
    }

    @Override
    public void modifyOne(CourseType courseType) {
        courseTypeMapper.updateOne(courseType);
    }
}