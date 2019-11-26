package com.woniu.fitness.view;


import com.woniu.fitness.model.Dynamic;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Set;

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

    //自增
    public Long incr(String key){
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return operations.increment(key, 1);
    }

    //根据key获取value
    public Integer getValue(String key){
        //获取操作String数据类型的对象
        ValueOperations<String, Object> stringValues = redisTemplate.opsForValue();
        String value= (String) stringValues.get(key);
        Integer views=Integer.parseInt(value);
        return views;
    }

    //添加点赞 set集合
    public Long addLikes(String key,Integer value){
        return redisTemplate.opsForSet().add(key,""+value);
    }

    //取消点赞
    public Long removeLikes(String key,Integer value){
        return redisTemplate.opsForSet().remove(key,""+value);
    }

    //是否点过赞
    public boolean isLikes(String key,Integer value){
        String user_id=""+value;
        return redisTemplate.opsForSet().isMember(key,user_id);
    }

    //判断点赞的人数
    public Long size(String key){
        return redisTemplate.opsForSet().size(key);
    }

    //
    public Set<Object> list(String key){
        return redisTemplate.opsForSet().members(key);
    }

    //封装浏览数，点赞数,是否点赞
    public void addViewsAndLikes(Dynamic dynamic, Integer user_id){
        String likes_key="dynamic-"+dynamic.getDynamic_id();
        //是否点赞
        Boolean flag=isLikes(likes_key,user_id);
        if(flag){
            dynamic.setIsLike(1); //点赞为1
        }else{
            dynamic.setIsLike(0); //未点赞为0
        }
        //点赞数
        if(size(likes_key)>0){ //如果点赞数大于0
            Integer dynamic_likes= Math.toIntExact(size(likes_key));
            dynamic.setDynamic_likes(dynamic_likes);
        }else{
            dynamic.setDynamic_likes(0);
        }
        //浏览数
        String views_key="dynamic:"+dynamic.getDynamic_id();
        if(hasKey(views_key)){
            Integer dynamic_views= getValue(views_key);
            dynamic.setDynamic_views(dynamic_views);
        }else{
            dynamic.setDynamic_views(0);
        }
    }

    //封装浏览数，点赞数 没有是否点赞
    public void addNoIsLike(Dynamic dynamic){
        String likes_key="dynamic-"+dynamic.getDynamic_id();
        //点赞数
        if(size(likes_key)>0){ //如果点赞数大于0
            Integer dynamic_likes= Math.toIntExact(size(likes_key));
            dynamic.setDynamic_likes(dynamic_likes);
        }else{
            dynamic.setDynamic_likes(0);
        }
        //浏览数
        String views_key="dynamic:"+dynamic.getDynamic_id();
        if(hasKey(views_key)){
            Integer dynamic_views= getValue(views_key);
            dynamic.setDynamic_views(dynamic_views);
        }else{
            dynamic.setDynamic_views(0);
        }
    }










}
