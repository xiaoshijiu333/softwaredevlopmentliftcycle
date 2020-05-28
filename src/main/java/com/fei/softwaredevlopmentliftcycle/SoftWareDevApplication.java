package com.fei.softwaredevlopmentliftcycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.fei.softwaredevlopmentliftcycle.mapper")
public class SoftWareDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftWareDevApplication.class, args);
    }

}
