package com.frw.app;

import com.frw.ctrl.HelloCtrl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fruwei on 2016/7/19.
 */
@Configuration
@ComponentScan(basePackages = {"com.frw"})
@EnableAutoConfiguration
public class AppMain {

    public static void main(String args[]) {
        SpringApplication.run(AppMain.class, args);
    }
}
