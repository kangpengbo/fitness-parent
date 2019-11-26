package com.woniu.fitness.redisSession.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/23
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/")
public class Hello {
    @RequestMapping(value = "/set", method = RequestMethod.GET)
    public Map<String, Object> firstResp (HttpServletRequest request){
        System.out.println(request.getSession().getId()+"||"+"sessionId");
        request.getSession().setAttribute("testKey", "testValue");
        Map<String, Object> map = new HashMap<>();
        System.out.println(request.getRequestURI());
        map.put("testKey", "testValue");
        return map;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Object sessions (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        System.out.println(request.getSession().getAttribute("testKey"));
        System.out.println(request.getSession().getId()+"||"+"sessionId");
        System.out.println(request.getServerPort());
        System.out.println("-------------------------------------");
        Cookie[] cookies=request.getCookies();
        System.out.println(cookies.length);
        if(cookies!=null){
            for(Cookie cookie:cookies){
                System.out.println(cookie.getValue()+"||"+cookie.getName());
            }
        }
        System.out.println("-------------------------------------");
        map.put("testKey", request.getSession().getAttribute("testKey"));
        return map;
    }

}