package com.zqxsober.micro.service.message.feign;

import com.zqxsober.api.message.api.MessageApi;
import com.zqxsober.api.message.pojo.dto.MessageDTO;
import com.zqxsober.micro.service.message.components.MessageComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: zqxsober
 * @Description: UserFeignImpl 类
 * @Date: 2022-01-04 17:18
 */
@RefreshScope
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageFeignImpl implements MessageApi {

    private final MessageComponent messageComponent;


    @Override
    public ResponseEntity<?> saveAndSend(MessageDTO dto) {
        messageComponent.addOne(dto);
        return null;
    }
}
