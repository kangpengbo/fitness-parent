package com.woniu.fitness.utils;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author 11718
 * @create 2019/11/19
 * @since 1.0.0
 */
public class FileUrlUtil {

    //判断文件是否有效
    public static String testUrl(String filepath){
        try {
            URL pathUrl = new URL(filepath);
            HttpURLConnection urlcon = (HttpURLConnection) pathUrl.openConnection();
            if(urlcon.getResponseCode()>=400){
                return "文件不存在";
            }else{
                return "文件存在";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return "请求失败";
        }
    }
}
