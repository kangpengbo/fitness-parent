package com.woniu.fitness.controller;

import com.woniu.fitness.model.User;
import com.woniu.fitness.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/18
 * @since 1.0.0
 */
@Controller
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("list")
    public List<User> list(){
        List<User> list=userService.findAll();
        System.out.println(list);
        return list;
    }
}