package com.offcn.webui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.reflect.Array;

/**
 * @author wzy
 * @version 0.0.3
 * @description WebUiStart
 * @since 2020/12/7 16:19
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//排除数据源jar包
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class WebUiStart {

    public static void main(String[] args) {

        SpringApplication.run(WebUiStart.class);
       

    }

}
