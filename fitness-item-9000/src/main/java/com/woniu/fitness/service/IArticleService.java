package com.woniu.fitness.service;

import com.woniu.fitness.model.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IArticleService {

    //增加文章
    int add(Article article);

    //删除文章
    int delete(int id);

    //撤下和复原文章
    int operate(@Param("id") int id, @Param("state") int state);

    //修改文章
    int update(Article article);

    //查询文章
    List<Article> findAll(String message);

    //查询单条文章
    Article findById(int id);

    //点赞
    void addLike(int id);

    //撤销点赞
    void subtractLike(int id);

    //浏览+1
    void addView(int id);
}
