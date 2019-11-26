package com.woniu.fitness.jwt.jwtController;


import com.alibaba.fastjson.JSONObject;
import com.woniu.fitness.jwt.annotation.UserLoginToken;
import com.woniu.fitness.jwt.jwtService.TokenService;
import com.woniu.fitness.model.User;
import com.woniu.fitness.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/22
 * @since 1.0.0
 */
@RestController
@RequestMapping("jwt")
public class JwtController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    TokenService tokenService;
    /*用户登录*/
    @RequestMapping("/login")
    public void login(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        User userForBase = userService.findByAccount(user.getAccount());
        if (userForBase == null) {
            jsonObject.put("message", "登录失败,用户不存在");
            // return jsonObject;
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                jsonObject.put("message", "登录失败,密码错误");
                // return jsonObject;
            } else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);

//                存在cookie中
                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(3600);
                //cookie.setDomain("localhost");
                cookie.setDomain("api.woniu.com");
                cookie.setPath("/");
                response.addCookie(cookie);
                Cookie cookie2 = new Cookie("account", "jack");
                cookie2.setMaxAge(3600);
                //cookie.setDomain("localhost");
                cookie2.setDomain("api.woniu.com");
                cookie2.setPath("/");
                response.addCookie(cookie2);
                response.setCharacterEncoding("utf-8");
                response.getWriter().print("success");
                //   return jsonObject;
            }
        }
    }
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }

}