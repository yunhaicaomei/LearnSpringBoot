package com.singaldatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@MapperScan("com.singaldatasource.dao")
public class SingaldatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingaldatasourceApplication.class, args);
    }

}

