package com.woniu.fitness.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
public class Course {
    private Integer course_id;
    private String course_name;
    private String introduction;
    private Integer course_time;
    private Integer fat_burning;
    private String difficulty;
    private String instrument;
    private String video;
    private String crowd;
    private Integer is_pay;
    private Integer type_id;
    private Integer course_state;
}