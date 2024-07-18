package com.zqxsober.api.order.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: zqxsober
 * @Description: OrderApi 类
 * @Date: 2022-07-19 14:04
 */
@FeignClient(value = "zqxsober-micro-service-order", path = "/order")
public interface OrderApi {

}
