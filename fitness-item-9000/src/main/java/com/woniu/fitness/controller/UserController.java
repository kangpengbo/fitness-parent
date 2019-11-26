package com.woniu.fitness.controller;

import com.woniu.fitness.jwt.jwtService.TokenService;
import com.woniu.fitness.model.User;
import com.woniu.fitness.response.ResponseResult;
import com.woniu.fitness.service.UserServiceImpl;
import com.woniu.fitness.utils.EmailUtil;
import com.woniu.fitness.utils.MD5Maker;
import com.woniu.fitness.wrapper.UserCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/18
 * @since 1.0.0
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    TokenService tokenService;

    @RequestMapping("list")
    public List<User> list(@RequestParam(defaultValue = "") String message,@RequestParam(defaultValue = "3") int info) {
        List<User> list = userService.findAll(message, info);
        return list;
    }

    @RequestMapping("/sendEmail")
    public String sendEmail(HttpSession session, String email) {
        String random = (int) (Math.random() * 100000) + "";
        redisTemplate.opsForValue().set(email, random);
        EmailUtil.sendEmail(email, "您好，您的验证码为:" + random);
        return "邮件已经发送成功!请填写验证码!";
    }

    @RequestMapping("/register")
    public int register(@RequestBody UserCode userCode, HttpSession session) {
        //验证码是否正确
        String sendCode = redisTemplate.opsForValue().get(userCode.getUser().getEmail());
        //删除redis缓存
        redisTemplate.delete(userCode.getUser().getEmail());
        if (!userCode.getCode().equals(sendCode)) {
            return 0;
        }
        //账户是否存在
        User u = userService.findOneByAccount(userCode.getUser().getAccount());
        if (u != null) {
            return 1;
        }
        //盐值加密
        String password = MD5Maker.stringToMd5StringWithSalt(userCode.getUser().getPassword(), userCode.getUser().getAccount());
        userCode.getUser().setPassword(password);
        userService.addUser(userCode.getUser());
        return 2;
    }

    //拉黑和还原用户
    @RequestMapping("/operate")
    public ResponseResult operateUser(int id, int state) {
        if (state == 1) {
            userService.defriend(id);
            return new ResponseResult("200", "已成功拉黑!");
        } else {
            userService.restore(id);
            return new ResponseResult("200", "已成功复原!");
        }
    }

    /*用户信息修改*/
    @RequestMapping("/update")
    public ResponseResult update(@RequestBody User user) {
        userService.update(user);
        return new ResponseResult("200", "修改成功!");
    }

    /*密码修改*/
    @RequestMapping("/updatePassword")
    public ResponseResult updatePassword(User user) {
        userService.updatePassword(user);
        return new ResponseResult("200", "修改成功!");
    }

    /*用户登录*/
    @RequestMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
        System.out.println(user);
        User user1 = userService.findByAccount(user.getAccount());
        System.out.println(user);
        //盐值加密
        String password = MD5Maker.stringToMd5StringWithSalt(user.getPassword(), user.getAccount());
        if (user1 == null) {
            return "0";
        } else {
            if (user1.getPassword().equals(password)) {
                String token = tokenService.getToken(user1);
               // 存在cookie中
                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(3600);
                //cookie.setDomain("localhost");
                cookie.setDomain("api.woniu.com");
                cookie.setPath("/");
                response.addCookie(cookie);
                Cookie cookie2 = new Cookie("account", user1.getAccount());
                cookie2.setMaxAge(3600);
                //cookie.setDomain("localhost");
                cookie2.setDomain("api.woniu.com");
                cookie2.setPath("/");
                response.addCookie(cookie2);
                response.setCharacterEncoding("utf-8");
                request.getSession().setAttribute("user", user1);
                return "1";
            } else {
                return "0";
            }
        }
    }

    //根据用户名查询单个用户，用于个人中心展示
    @RequestMapping("/findOne")
    public ResponseResult findOneByAccount(String account) {
        User user = userService.findOneByAccount(account);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal2.setTime(user.getBirthday());
        int age = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR) + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("age", age);
        return new ResponseResult("200", "查询成功").setMap(map);
    }

    //关注用户
    @RequestMapping("/addAttention")
    public ResponseResult addAttention(int user_id, int fan_id) {
        userService.addAttention(user_id, fan_id);
        return new ResponseResult("200", "关注成功");
    }

    //取关用户
    @RequestMapping("/removeAttention")
    public ResponseResult removeAttention(int user_id, int fan_id) {
        userService.removeAttention(user_id, fan_id);
        return new ResponseResult("200", "取关成功");
    }

    //根据user_id查询所有的粉丝
    @RequestMapping("/getAllFans")
    public ResponseResult getAllFans(int user_id) {
        List<User> list = userService.findAllFans(user_id);
        return new ResponseResult("200", "查询成功!").add("fanlist", list);
    }

    //根据user_id查询所有的已关注用户
    @RequestMapping("/getAllAttentions")
    public ResponseResult getAllAttentions(int user_id) {
        List<User> list = userService.findAllAttention(user_id);
        return new ResponseResult("200", "查询成功!").add("attentionlist", list);
    }
}
