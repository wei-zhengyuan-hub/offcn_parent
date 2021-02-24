package com.offcn.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 魏正源
 * @version V1.0
 * @Package com.offcn.project
 * @date 2020/12/1 18:40
 * @Copyright © 2020-2021 中公教育
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.offcn.project.mapper")
public class ScwProJectStart {

    public static void main(String[] args) {

        SpringApplication.run(ScwProJectStart.class);

    }

}
