package com.woniu.fitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/11
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
public class Item_App9000 {
    public static void main(String[] args) {
        SpringApplication.run(Item_App9000.class,args);
    }
}