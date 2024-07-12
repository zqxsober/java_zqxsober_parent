package com.zqxsober.micro.service.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: zqxsober
 * @Description: ServiceUserApplication 类
 * @Date: 2022-01-04 16:26
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.zqxsober.micro.service.user.feign"})
@EntityScan(value = {"com.zqxsober.micro.service.user.pojo.po"})
@MapperScan(value = {"com.zqxsober.micro.service.user.mapper"})
public class MicroServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceUserApplication.class);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
