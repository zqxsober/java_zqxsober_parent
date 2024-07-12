package com.zqxsober.api.product.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: zqxsober
 * @Description: ProductApi 类
 * @Date: 2024-01-19 14:04
 */
@FeignClient(value = "zqxsober-micro-service-product", path = "/product")
public interface ProductApi {


}
