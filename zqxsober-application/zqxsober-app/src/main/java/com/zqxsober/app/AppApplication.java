package com.zqxsober.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Author: zqxsober
 * @Description: AppApplication 类
 * @Date: 2022-07-19 09:35
 */
@EnableOpenApi
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.zqxsober"})
@SpringBootApplication(scanBasePackages = {"com.zqxsober"}, exclude= {DataSourceAutoConfiguration.class})
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class);
    }
}
