package com.zqxosber.web.module.user.controller;

import com.zqxosber.web.support.BaseController;
import com.zqxsober.api.user.pojo.bo.user.UserInfoBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Api(tags = "会员接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends BaseController {


    @GetMapping("/getUser")
    @ApiOperation(value = "获取会员信息", response = String.class)
    public ResponseEntity<UserInfoBo> getUser(@RequestParam(required = false) String userId) {
        log.info("获取会员信息，请求参数：【{}】", userId);
        return ResponseEntity.ok(super.getUserInfo(userId));
    }

}
