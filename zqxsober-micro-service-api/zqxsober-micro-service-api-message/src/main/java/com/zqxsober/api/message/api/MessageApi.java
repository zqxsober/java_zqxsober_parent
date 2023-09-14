package com.zqxsober.api.message.api;

import com.zqxsober.api.message.pojo.dto.MessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: zqxsober
 * @Description: UserApi 类
 * @Date: 2022-07-19 14:04
 */
@FeignClient(value = "zqxsober-micro-service-message", path = "/message")
public interface MessageApi {

    /**
     * 发送消息参数
     * @param dto       用户id
     * @return          用户信息
     */
    @PostMapping("/saveAndSend")
    ResponseEntity<?> saveAndSend(@RequestBody MessageDTO dto);

}
