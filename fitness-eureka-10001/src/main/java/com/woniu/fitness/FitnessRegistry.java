package com.woniu.fitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/11
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaServer
public class FitnessRegistry {
    public static void main(String[] args) {
        SpringApplication.run(FitnessRegistry.class,args);
    }
}