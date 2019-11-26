package com.woniu.fitness.service;

import com.woniu.fitness.model.CourseOrder;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ICourseOrderService {

    //生成订单
    int creatOrder(CourseOrder courseOrder) throws ParseException;

    //用户查询自己的订单
    List<CourseOrder> findByUser_id(int user_id);

    //用户删除订单，修改订单状态
    int remove(String order_number) throws ParseException;
}
