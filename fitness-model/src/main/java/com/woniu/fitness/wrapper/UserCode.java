package com.woniu.fitness.wrapper;

import com.woniu.fitness.model.User;
import lombok.Data;

@Data
public class UserCode {
    private User user;
    private String code;
}
