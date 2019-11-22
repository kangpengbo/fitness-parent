package com.woniu.fitness.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

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

    //判断是图片还是视频
    public static String judgeFile(String filePath){
        System.out.println(filePath);
        String[] images={"jpg","jpeg","png","gif","bmp"};
        String[] videos={"mp4","avi","mov","rmvb","rm","wmn","mkv","mlv"};
        String[] str=filePath.split("\\.");
        System.out.println(str.length);
        //文件后缀名
        String format=str[str.length-1];
        System.out.println(format);
        for(String img:images){
            if(img.equals(format)){
                return "图片";
            }
        }
        for(String video:videos){
            if(video.equals(format)){
                return "视频";
            }
        }
        return "错误类型";
    }
}
