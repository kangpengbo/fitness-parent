package com.woniu.fitness.mapper;

import com.woniu.fitness.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAll();
}
