package com.woniu.fitness.service;

import com.woniu.fitness.mapper.CourseMapper;
import com.woniu.fitness.mapper.CourseOrderMapper;
import com.woniu.fitness.mapper.UserMapper;
import com.woniu.fitness.model.Course;
import com.woniu.fitness.model.CourseOrder;
import com.woniu.fitness.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin2.message.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CourseOrderServiceImpl implements ICourseOrderService {
    @Autowired
    private CourseOrderMapper courseOrderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;

    //生成订单
    @Override
    public int creatOrder(CourseOrder courseOrder) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String local1 = sdf.format(new Date());
        Date local = sdf.parse(local1);
        courseOrder.setCreate_time(local);
        courseOrder.setChange_time(local);
        courseOrder.setOrder_number(KeyUtil.generateUniqueKey());
        //通过user_id查询是否拥有该课程
        List<Course> list = courseMapper.selectByUserId(courseOrder.getUser_id());
        for (Course c : list) {
            if (c.getCourse_id() == courseOrder.getCourse_id()) {
                return 2;
            }
        }
        //根据user_id查询用户判断金额是否足够
        if (userMapper.selectById(courseOrder.getUser_id()).getMoney() >= courseOrder.getAmount()) {
            //消费金额
            userMapper.consume(courseOrder.getUser_id(), courseOrder.getAmount());
        } else {
            return 0;
        }
        //生成用户订单中间表
        courseOrderMapper.insertUserCourse(courseOrder.getUser_id(), courseOrder.getCourse_id());
        //生成订单表
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
        //通过user_id查询是否拥有该课程
        List<Course> list = courseMapper.selectByUserId(user_id);
        for (Course c : list) {
            if (c.getCourse_id() == course_id) {
                return 0;
            }
        }
        return courseOrderMapper.insertUserCourse(user_id, course_id);
    }
}
