package com.woniu.fitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author FeianLing
 * @create 2019/11/11
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class FitnessApiGateway {
    public static void main(String[] args) {
        SpringApplication.run(FitnessApiGateway.class, args);
    }
}