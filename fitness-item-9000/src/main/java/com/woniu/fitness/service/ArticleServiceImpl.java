package com.woniu.fitness.service;

import com.woniu.fitness.mapper.ArticleMapper;
import com.woniu.fitness.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public int add(Article article) {
        article.setCreate_time(new Date());
        int result = articleMapper.insert(article);
        redisTemplate.opsForHash().put(article.getArticle_id() + "", "article_likes", 0);
        redisTemplate.opsForHash().put(article.getArticle_id() + "", "article_views", 0);
        return result;
    }

    @Override
    public int delete(int id) {
        return articleMapper.delete(id);
    }

    @Override
    public int operate(int id, int state) {
        return articleMapper.operate(id, state);
    }

    @Override
    public int update(Article article) {
        return articleMapper.update(article);
    }

    @Override
    @Transient
    public List<Article> findAll(String message) {
        List<Article> list = articleMapper.selectAll(message);
        for (Article a : list) {
            int likes = (int) redisTemplate.opsForHash().get(a.getArticle_id() + "", "article_likes");
            int views = (int) redisTemplate.opsForHash().get(a.getArticle_id() + "", "article_views");
            a.setArticle_likes(likes);
            a.setArticle_views(views);
        }
        return list;
    }

    @Override
    @Transient
    public Article findById(int id) {
        Article article = articleMapper.selectById(id);
        int likes = (int) redisTemplate.opsForHash().get(article.getArticle_id() + "", "article_likes");
        int views = (int) redisTemplate.opsForHash().get(article.getArticle_id() + "", "article_views");
        article.setArticle_likes(likes);
        article.setArticle_views(views);
        return article;
    }

    @Override
    public void addLike(int id) {
        int count = (int) redisTemplate.opsForHash().get(id + "", "article_likes");
        count++;
        redisTemplate.opsForHash().put(id + "", "article_likes", count);
    }

    @Override
    public void subtractLike(int id) {
        int count = (int) redisTemplate.opsForHash().get(id + "", "article_likes");
        count--;
        redisTemplate.opsForHash().put(id + "", "article_likes", count);
    }

    @Override
    public void addView(int id) {
        int count = (int) redisTemplate.opsForHash().get(id + "", "article_views");
        count++;
        redisTemplate.opsForHash().put(id + "", "article_views", count);
    }
}
