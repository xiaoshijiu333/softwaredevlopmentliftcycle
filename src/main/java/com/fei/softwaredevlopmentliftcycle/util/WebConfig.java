package com.fei.softwaredevlopmentliftcycle.util;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/12/19
 * @Description: Web的一些配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 接受文件大小配置
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 文件最大
        // DataSize.ofMegabytes(10L),10MB
        factory.setMaxFileSize(DataSize.ofMegabytes(5));
        // 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(5));
        return factory.createMultipartConfig();
    }

}
