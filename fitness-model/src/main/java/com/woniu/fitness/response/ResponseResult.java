package com.woniu.fitness.response;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author 11718
 * @create 2019/9/28
 * @since 1.0.0
 */
public class ResponseResult {
    private String code;//200正确    500错误
    private String message;
    private Map<String,Object> map=new HashMap<>();

    public ResponseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public ResponseResult(){

    }

    public ResponseResult add(String key, Object value){
        map.put(key,value);
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public ResponseResult setMap(Map<String, Object> map) {
        this.map = map;
        return this;
    }
}
