package com.zqxsober.micro.service.message.components;

import com.zqxsober.api.product.pojo.dto.MessageDTO;
import com.zqxsober.micro.service.message.pojo.po.MqMessageLog;
import com.zqxsober.micro.service.message.service.MqMessageLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Author: zqxsober
 * @Description: MessageComponent 类
 * @Date: 2022-08-08 17:51
 */
@Component
@RequiredArgsConstructor
public class MessageComponent {

    private final MqMessageLogService mqMessageLogService;

    /**
     * 保存消息
     * @param messageDTO    消息dto
     * @return              消息id
     */
    public String addOne(MessageDTO messageDTO) {

        return "";
    }

    private MqMessageLog assembleMessage(MessageDTO messageDTO){
        MqMessageLog toSave = new MqMessageLog();
        return toSave;
    }

}
