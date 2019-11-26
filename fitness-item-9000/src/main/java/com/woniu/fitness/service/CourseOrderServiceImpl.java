package com.woniu.fitness.service;

import com.woniu.fitness.mapper.CourseOrderMapper;
import com.woniu.fitness.model.CourseOrder;
import com.woniu.fitness.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CourseOrderServiceImpl implements ICourseOrderService {
    @Autowired
    private CourseOrderMapper courseOrderMapper;

    //生成订单
    @Override
    public int creatOrder(CourseOrder courseOrder) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String local1 = sdf.format(new Date());
        Date local = sdf.parse(local1);
        courseOrder.setCreate_time(local);
        courseOrder.setChange_time(local);
        courseOrder.setOrder_number(KeyUtil.generateUniqueKey());
        courseOrderMapper.insertUserCourse(courseOrder.getUser_id(), courseOrder.getCourse_id());
        return courseOrderMapper.insert(courseOrder);
    }

    //用户查询自己的订单
    @Override
    public List<CourseOrder> findByUser_id(int user_id) {
        return courseOrderMapper.selectByUser_id(user_id);
    }

    //用户删除订单，修改订单状态
    @Override
    public int remove(String order_number) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String local1 = sdf.format(new Date());
        Date local = sdf.parse(local1);
        return courseOrderMapper.remove(order_number, local);
    }

    //根据user_id和course_id更新中间表
    @Override
    public int insertUserCourse(int user_id, int course_id) {
        return courseOrderMapper.insertUserCourse(user_id, course_id);
    }
}
