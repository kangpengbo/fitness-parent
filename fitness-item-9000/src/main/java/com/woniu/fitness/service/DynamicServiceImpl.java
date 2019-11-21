package com.woniu.fitness.service;

import com.woniu.fitness.mapper.DynamicMapper;
import com.woniu.fitness.model.Dynamic;
import com.woniu.fitness.vo.DynamicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author 11718
 * @create 2019/11/19
 * @since 1.0.0
 */
@Service
@Transactional
public class DynamicServiceImpl implements IDynamicService{
    @Autowired
    private DynamicMapper dynamicMapper;

    //增
    @Override
    public int addDynamic(Dynamic dynamic) {
        return dynamicMapper.insertDynamic(dynamic);
    }

    //删
    @Override
    public int removeDynamic(int dynamic_id) {
        return dynamicMapper.deleteDynamic(dynamic_id);
    }

    //查所有 用户
    @Override
    public List<Dynamic> findAllDynamic(int user_id) {
        return dynamicMapper.selectAllDynamic(user_id);
    }

    //查所有 广场
    @Override
    public List<Dynamic> findAllNOCondition() {
        return dynamicMapper.selectAllNOCondition();
    }

    //查单个
    @Override
    public Dynamic findOneDynamic(int dynamic_id) {
        return dynamicMapper.selectOneDynamicById(dynamic_id);
    }

    //所有主键
    @Override
    public List<Integer> findAllId() {
        return dynamicMapper.selectAllId();
    }

    @Override
    public int modifyViews(int dynamic_id, int views) {
        return dynamicMapper.updateViews(dynamic_id,views);
    }

    //=======================================================================================

    //查列表
    @Override
    public List<Dynamic> findAll(DynamicVo vo) {
        return dynamicMapper.selectAll(vo);
    }
}
