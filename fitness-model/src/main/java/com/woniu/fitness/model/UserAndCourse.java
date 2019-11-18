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
public class UserAndCourse {
    private Integer user_id;
    private Integer course_id;
}