package com.zqxsober.app.module.message.controller;

import com.zqxsober.common.mq.constant.MqConstant;
import com.zqxsober.common.mq.producer.MessageProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: zqxsober
 * @Description: UserController 类
 * @Date: 2022-07-19 09:43
 */
@Slf4j
@Api(tags = "消息接口")
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {


    private final MessageProducer messageProducer;

    private final AsyncTaskExecutor executor;

    @PostMapping("/delayMsg")
    @ApiOperation(value = "延时消息")
    public ResponseEntity<?> addPoint(@ApiParam("延时时间")@RequestParam Integer seconds,
                                      @ApiParam("消息内容") @RequestParam String content){
        return ResponseEntity.ok(true);
    }

    @PostMapping("/normalMsg")
    @ApiOperation(value = "普通消息")
    public ResponseEntity<?> normalMsg(@ApiParam("消息内容") @RequestParam String content){
        return ResponseEntity.ok(true);
    }

}
