package com.mutildatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.mutildatasource.dao")
public class MutildatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutildatasourceApplication.class, args);
    }

}

