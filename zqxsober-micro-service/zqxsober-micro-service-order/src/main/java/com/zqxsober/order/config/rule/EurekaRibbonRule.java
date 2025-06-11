package com.zqxsober.order.config.rule;

import com.alibaba.fastjson.JSON;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: zqxsober
 * @Description: EurekaRibbonRule 类
 * @Date: 2022-07-25 15:59
 */
@Slf4j
public class EurekaRibbonRule extends AbstractLoadBalancerRule {


    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private EurekaDiscoveryClient discoveryClient;


    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        try {
            List<ServiceInstance> instances = discoveryClient.getInstances(applicationName);
            log.info("服务列表：【{}】", JSON.toJSONString(instances));
            return null;
        } catch (Exception e) {
            log.error("获取{}实例异常", applicationName);
        }
        return null;
    }
}
