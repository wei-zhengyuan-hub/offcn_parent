package com.offcn.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wzy
 * @version 0.0.3
 * @description ScwOrderStart
 * @since 2020/12/3 14:56
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
//@SpringCloudApplication //@SpringBootApplication+@EnableDiscoveryClient+@EnableCircuitBreaker
@MapperScan("com.offcn.order.mapper")
public class ScwOrderStart {
    public static void main(String[] args) {

        SpringApplication.run(ScwOrderStart.class);

    }
}
