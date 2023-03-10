package com.zqxosber.web.module.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.zqxsober.api.user.pojo.bo.UserInfoBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zqxsober
 * @Description: UserController 类
 * @Date: 2022-07-19 09:43
 */
@Api(tags = "会员接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    @GetMapping("/getUser")
    @ApiOperation(value = "获取会员信息", response = String.class)
    public ResponseEntity<?> getUser(@RequestParam String userId){
        return ResponseEntity.ok("user info...");
    }

}
