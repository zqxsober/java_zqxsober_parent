package com.zqxsober.api.message.pojo.dto;

import com.zqxsober.common.mq.emums.MqTopicEnum;
import lombok.Builder;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: zqxsober
 * @Description: MessageDTO 类
 * @Date: 2022-08-08 17:25
 */
@Builder
@ToString
public class MessageDTO {

    /**
     * 消息topic
     */
    private MqTopicEnum topicEnum;

    /**
     * 消息id
     */
    @Builder.Default
    private String msgId;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 延时时间，单位-秒
     */
    private Integer delayTime;

}
