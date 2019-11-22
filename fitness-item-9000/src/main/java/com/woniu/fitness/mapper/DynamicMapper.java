package com.woniu.fitness.mapper;

import com.woniu.fitness.model.Dynamic;
import com.woniu.fitness.vo.DynamicVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author 11718
 * @create 2019/11/19
 * @since 1.0.0
 */
@Mapper
public interface DynamicMapper {
    //增
    int insertDynamic(Dynamic dynamic);

    //删
    int deleteDynamic(int dynamic_id);

    //查List 用户
    List<Dynamic> selectAllDynamic(int user_id);

    //查list 所有
    List<Dynamic> selectAllNOCondition();

    //查One
    Dynamic selectOneDynamicById(int dynamic_id);

    //查所有主键
    List<Integer> selectAllId();

    //更新数据库浏览数
    int updateViews(int dynamic_id, int views);

    //================================================================

    //查列表
    List<Dynamic> selectAll(DynamicVo vo);










}
