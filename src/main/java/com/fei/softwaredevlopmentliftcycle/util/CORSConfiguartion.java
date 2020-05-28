package com.fei.softwaredevlopmentliftcycle.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/12/15
 * @Description: WebMVC的一些配置
 */
@Configuration
public class CORSConfiguartion implements WebMvcConfigurer {

    /**
     * 全局跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }
}
