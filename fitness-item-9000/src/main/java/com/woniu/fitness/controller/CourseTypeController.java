package com.woniu.fitness.controller;

import com.woniu.fitness.model.CourseType;
import com.woniu.fitness.service.CourseTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/21
 * @since 1.0.0
 */
@RestController
@RequestMapping("courseType")
public class CourseTypeController {
    @Autowired
    private CourseTypeServiceImpl courseTypeService;
    @RequestMapping("list")
    public List<CourseType> list(){
        List<CourseType> list=courseTypeService.findAll();
        return list;
    }

    @RequestMapping("add")
    public String addOne(@RequestBody CourseType courseType){
        System.out.println(courseType);
        courseTypeService.addOne(courseType);
        return "success";
    }

    @RequestMapping("remove")
    public String removeOne(int id){
        System.out.println(id);
        courseTypeService.removeOne(id);
        return "success";
    }

    @RequestMapping("modify")
    public String modifyOne(@RequestBody CourseType courseType){
        System.out.println(courseType);
        courseTypeService.modifyOne(courseType);
        return "success";
    }
}