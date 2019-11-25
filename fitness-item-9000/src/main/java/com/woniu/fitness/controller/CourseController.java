package com.woniu.fitness.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.fitness.model.Course;
import com.woniu.fitness.model.Dynamic;
import com.woniu.fitness.response.ResponseResult;
import com.woniu.fitness.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/19
 * @since 1.0.0
 */
@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseServiceImpl courseService;
    //查询全部课程
    @GetMapping("list")
    public List<Course> list(){
        return courseService.findAll();
    }
    //根据课程名称查询
    @RequestMapping("findByName")
    public ResponseResult findByName(String name,@RequestParam(value = "pageNow",defaultValue = "1")Integer pageNow){
        System.out.println(name);
        PageHelper.startPage(pageNow,3); //每页3条，可修改
        List<Course> courses=courseService.findByName(name);
        PageInfo<Dynamic> pageInfo=new PageInfo(courses,3);
        return new ResponseResult("200","操作成功！").add("pageInfo",pageInfo);
    }
    //查询单个课程
    @GetMapping("findOne")
    public Course findOne(@RequestBody Integer id){
        System.out.println(id);
        return courseService.findOne(id);
    }
    //添加课程
    @PostMapping("addOne")
    public String addOne(@RequestBody Course course){
        System.out.println(course);
        courseService.addOne(course);
        return "success";
    }
    //修改课程
    @PostMapping("modifyOne")
    public String modifyOne(@RequestBody Course course){
        System.out.println(course);
        courseService.modifyOne(course);
        return "success";
    }
    //删除课程g
    @RequestMapping("removeOne")
    public String removeOne(int id){
        System.out.println(id);
        courseService.removeOne(id);
        return "success";
    }
}