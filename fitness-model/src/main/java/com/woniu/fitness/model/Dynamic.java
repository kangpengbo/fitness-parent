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
 * @author 11718
 * @create 2019/11/18
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Dynamic {
    private Integer dynamic_id;
    private Integer user_id;
    private String user_account;
    private String dynamic_content;
    private String dynamic_image;
    private String dynamic_video;
    private Integer dynamic_views;
    private Integer dynamic_likes;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;
    private Integer state;
















}
