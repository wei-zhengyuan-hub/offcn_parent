package com.offcn.webui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wzy
 * @version 0.0.3
 * @description AppWebMvcConfig
 * @since 2020/12/7 16:32
 */
@Configuration
public class AppWebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加视图控制器
     * @param registry
     */
    public void addViewControllers(ViewControllerRegistry registry){
        //如果controller仅仅用于转发页面，那么在当前方法中配置映射即可，这样就可以直接访问登录页了
        registry.addViewController("login.html").setViewName("login");

    }

}
