package com.example;

import com.yuye.metadata.annotation.MetadataModelScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MetadataModelScan(basePackage = {"com.example.metadata.mapper"})
@MapperScan({"com.example.dao"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
