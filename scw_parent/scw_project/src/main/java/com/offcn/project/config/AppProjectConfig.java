package com.offcn.project.config;

import com.offcn.utils.OssTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 魏正源
 * @version V1.0
 * @Package com.offcn.project.config
 * @date 2020/12/1 19:14
 * @Copyright © 2020-2021 中公教育
 * 跨模块加载云存储工具类
 */
@Configuration
public class AppProjectConfig {

    @ConfigurationProperties(prefix = "oss")
    @Bean
    public OssTemplate ossTemplate(){
        return new OssTemplate();
    }

}
