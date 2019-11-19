package com.woniu.fitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author wugd
 * @create 2019/11/12
 * @since 1.0.0
 */
@EnableEurekaClient
@SpringBootApplication
public class Upload_App {
    public static void main(String[] args) {
        SpringApplication.run(Upload_App.class,args);
    }
}