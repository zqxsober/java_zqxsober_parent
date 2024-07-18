package com.zqxsober.app.module.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.zqxsober.api.order.api.UserApi;
import com.zqxsober.api.order.pojo.bo.order.UserInfoBo;
import com.zqxsober.app.module.user.pojo.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class UserController {

    private final UserApi userApi;

    @Value("${spring.application.name}")
    private String applicationName;


    @GetMapping("/getById")
    @ApiOperation(value = "获取会员信息", response = UserInfoVO.class)
    public ResponseEntity<?> getById(@RequestParam String userId){
        ResponseEntity<UserInfoBo> response = userApi.getUserInfo(userId);
        if (response.getStatusCode().isError()) {
            throw new NullPointerException("找不到该会员");
        }
        return ResponseEntity.ok(BeanUtil.copyProperties(response.getBody(), UserInfoVO.class));
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取会员列表", response = UserInfoVO.class)
    public ResponseEntity<?> list(){
        List<UserInfoVO> list = Stream.of(new UserInfoVO( "张三", "1", 18L),
                new UserInfoVO( "李四", "2", 18L),
                new UserInfoVO( "王五", "3", 18L))
                .collect(Collectors.toList());
        log.info("Hello Nacos Discovery " + applicationName + " registered successfully!" );

        return ResponseEntity.ok(list);
    }




}
