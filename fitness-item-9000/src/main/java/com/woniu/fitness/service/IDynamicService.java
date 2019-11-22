package com.woniu.fitness.service;

import com.woniu.fitness.model.Dynamic;
import com.woniu.fitness.vo.DynamicVo;

import java.util.List;

public interface IDynamicService {
    //增
    int addDynamic(Dynamic dynamic);

    //删
    int removeDynamic(int dynamic_id);

    //查所有 用户
    List<Dynamic> findAllDynamic(int user_id);

    //查list 所有
    List<Dynamic> findAllNOCondition();

    //查单个
    Dynamic findOneDynamic(int dynamic_id);

    //所有主键
    List<Integer> findAllId();

    //更新浏览数
    int modifyViews(int dynamic_id, int views);

    //==================================================

    //查列表
    List<Dynamic> findAll(String sel);















}
