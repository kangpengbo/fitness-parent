package com.woniu.fitness.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/18
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Integer article_id;
    private String article_title;
    private String article_content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_time;
    private String article_image;
    private Integer article_views;
    private Integer article_likes;
    private Integer article_state;
}
