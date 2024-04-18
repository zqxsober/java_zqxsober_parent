package com.zqxsober.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: zqxsober
 * @Description: EchoController 类
 * @Date: 2023-09-21 15:31
 */
@RestController
@RefreshScope
public class EchoController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping(value = "/echo/discovery")
    public String echoDiscovery() {
        return "Hello Nacos Discovery " + applicationName + " registered successfully!" ;
    }

}
