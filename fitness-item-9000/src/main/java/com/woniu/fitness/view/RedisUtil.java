package com.woniu.fitness.view;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author 11718
 * @create 2019/11/21
 * @since 1.0.0
 */
@Component
public class RedisUtil {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    //删除缓存
    public void del(String key){
        redisTemplate.delete(key);
    }

    //判断key是否存在
    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    //第一次浏览，添加浏览量
    public void add(String key, String value){
        //获取操作String数据类型的对象
        ValueOperations<String, Object> stringValues = redisTemplate.opsForValue();
        stringValues.set(key,value);
    }

    //加一
    public Long incr(String key){
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return operations.increment(key, 1);
    }

    //根据key获取value
    public String getValue(String key){
        //获取操作String数据类型的对象
        ValueOperations<String, Object> stringValues = redisTemplate.opsForValue();
        return (String) stringValues.get(key);
    }


















}
