package com.woniu.fitness.mapper;

import com.woniu.fitness.model.Article;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ArticleMapper {

    //增加文章
    int insert(Article article);

    //删除文章
    int delete(int id);

    //撤下和复原文章
    int operate(@Param("id") int id,@Param("state") int state);

    //修改文章
    int update(Article article);

    //查询文章
    List<Article> selectAll(@Param("message") String message);

    //查询单条文章
    Article selectById(int id);
}
