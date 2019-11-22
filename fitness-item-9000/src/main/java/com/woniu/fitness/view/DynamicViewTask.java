package com.woniu.fitness.view;

import com.woniu.fitness.service.IDynamicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author 11718
 * @create 2019/11/21
 * @since 1.0.0
 */
@Component
@Slf4j
public class DynamicViewTask {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IDynamicService dynamicService;


    //定时任务，凌晨一点入库 浏览量
    @Scheduled(cron = "0 0 1 * * ? ")
    @Transactional(rollbackFor=Exception.class)
    public void updateViews(){
        log.info("===================入库开始=================");
        //所有动态id
        List<Integer> list=dynamicService.findAllId();
        list.forEach(dynamic_id->{
            String key="dynamic:"+dynamic_id;
            Integer views= redisUtil.getValue(key);
            if(views>0){
                int num=dynamicService.modifyViews(dynamic_id,views);
                if(num!=0){
                    log.info("数据库更新后的浏览量为"+views);
                }
            }
        });
        log.info("===================入库结束======================");
    }



}
