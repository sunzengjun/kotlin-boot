package com.szj.hello;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Sun on 2017/3/31.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println("begin");
        SpringApplication.run(Application.class, args);
    }
}
