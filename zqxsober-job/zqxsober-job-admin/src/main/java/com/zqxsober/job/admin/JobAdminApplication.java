package com.zqxsober.job.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author xuxueli 2018-10-28 00:38:13
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = {"com.zqxsober.job.admin.dao"})
public class JobAdminApplication {

	public static void main(String[] args) {
        SpringApplication.run(JobAdminApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}