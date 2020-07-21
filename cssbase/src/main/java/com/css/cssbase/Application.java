package com.css.cssbase;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
@MapperScan("com.css.cssbase.moudles.user.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
