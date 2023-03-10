package com.zqxsober.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zqxsober
 * @Description: OrderController 类
 * @Date: 2022-07-26 15:54
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/get")
    public ResponseEntity<?> getOrder(){
        log.info("get order...");
        return ResponseEntity.ok("order...");
    }
}
