package com.woniu.fitness.controller;

import com.woniu.fitness.model.CourseOrder;
import com.woniu.fitness.response.ResponseResult;
import com.woniu.fitness.service.ICourseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("courseOrder")
public class CourseOrderController {
    @Autowired
    private ICourseOrderService courseOrderService;

    //生成订单
    @RequestMapping("/creat")
    public ResponseResult creat(@RequestBody CourseOrder courseOrder) throws ParseException {
        courseOrderService.creatOrder(courseOrder);
        return new ResponseResult("200", "成功生成!");
    }

    //根据用户id查询订单
    @RequestMapping("/findByid")
    public ResponseResult findByUser_id(int user_id) {
        List<CourseOrder> list = courseOrderService.findByUser_id(user_id);
        return new ResponseResult("200", "查询成功!").add("courseOrderList", list);
    }

    //用户删除订单，修改订单状态
    @RequestMapping("/remove")
    public ResponseResult remove(String order_number) throws ParseException {
        courseOrderService.remove(order_number);
        return new ResponseResult("200", "删除成功!");
    }
}
