package com.woniu.fitness.jwt.jwtUtil;

import javax.servlet.http.Cookie;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/22
 * @since 1.0.0
 */
public class JwtCookieUtil {
    public static String getAccount(Cookie[] cookies){
        String account=null;
        if(cookies!=null){
            for (Cookie cookie:cookies){
                System.out.println("cookie");
                System.out.println(cookie.getName());
                if("account".equals(cookie.getName())){
                    account=cookie.getValue();
                    System.out.println(cookie.getValue());
                    return account;
                }

            }
        }
        return account;

    }

    public static String getTOken(Cookie[] cookies){
        String token=null;
        if(cookies!=null){
            for (Cookie cookie:cookies){
                System.out.println("cookie");
                System.out.println(cookie.getName());
                if("token".equals(cookie.getName())){
                    token=cookie.getValue();
                    System.out.println(cookie.getValue());
                    return token;
                }

            }
        }
        return token;

    }
}