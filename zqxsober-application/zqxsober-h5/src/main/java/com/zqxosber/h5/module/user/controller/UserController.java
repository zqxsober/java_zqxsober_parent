package com.zqxosber.h5.module.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zqxsober
 * @Description: UserController 类
 * @Date: 2022-07-26 15:09
 */
@Api(tags = "会员接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    @GetMapping("/get")
    @ApiOperation(value = "获取会员信息", response = String.class)
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok("user info...");
    }

}