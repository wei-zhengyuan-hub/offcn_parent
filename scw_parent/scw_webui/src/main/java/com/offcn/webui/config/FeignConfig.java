package com.offcn.webui.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author wzy
 * @version 0.0.3
 * @description FeignConfig
 * @since 2020/12/7 16:43
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level getFeignlogger(){
        return Logger.Level.FULL;
    }

}
