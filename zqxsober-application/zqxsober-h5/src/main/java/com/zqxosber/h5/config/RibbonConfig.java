package com.zqxosber.h5.config;

import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbon.RibbonConfiguration;

/**
 * @Author: zqxsober
 * @Description: RibbonConfig 类
 * @Date: 2022-07-25 17:21
 */
@Configuration
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class RibbonConfig {
}
