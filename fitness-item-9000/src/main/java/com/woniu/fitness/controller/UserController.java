package com.woniu.fitness.controller;

import com.woniu.fitness.model.User;
import com.woniu.fitness.service.UserServiceImpl;
import com.woniu.fitness.utils.EmailUtil;
import com.woniu.fitness.utils.MD5Maker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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

    @RequestMapping("/sendEmail")
    public String sendEmail(HttpSession session,String email){
        String code = (String) session.getAttribute("emailInfo");
        if (code!=null){
            session.removeAttribute("emailInfo");
        }
        String random=(int) (Math.random()*100000)+"";
        session.setAttribute("mailInfo",random);
        EmailUtil.sendEmail(email,"您好，您的验证码为:"+random);
        return "邮件已经发送成功!请填写验证码!";
    }

    @RequestMapping("/register")
    public int register(User user,String code,HttpSession session){
        //验证码是否正确
        String sendCode= (String) session.getAttribute("mailInfo");
        if(!sendCode.equals(code)){
            return 0;
        }
        //账户是否存在
        User u= userService.findOneByAccount(user.getAccount());
        if(u!=null){
            return 1;
        }
        //盐值加密
        String password= MD5Maker.stringToMd5StringWithSalt(user.getPassword(),user.getAccount());
        user.setPassword(password);
        userService.addUser(user);
        return 2;
    }
}
