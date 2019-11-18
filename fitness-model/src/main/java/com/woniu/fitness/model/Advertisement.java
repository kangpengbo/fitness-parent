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
public class Advertisement {
    private Integer ad_id;
    private String ad_title;
    private String ad_content;
    private String ad_image;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end_time;
    private Integer ad_state;
}