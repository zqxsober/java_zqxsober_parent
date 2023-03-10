package com.zqxosber.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Author: zqxsober
 * @Description: WebApplication 类
 * @Date: 2022-07-26 15:41
 */
@EnableOpenApi
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.zqxsober"})
@SpringBootApplication(scanBasePackages = {"com.zqxsober"}, exclude= {DataSourceAutoConfiguration.class})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}
