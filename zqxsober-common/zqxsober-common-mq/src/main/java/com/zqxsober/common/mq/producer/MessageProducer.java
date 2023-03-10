package com.zqxsober.common.mq.producer;

import com.alibaba.fastjson.JSON;
import com.zqxsober.common.mq.constant.GlobalConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @Author: zqxsober
 * @Description: RocketMQProducer 类
 * @Date: 2022-08-05 09:29
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {

    private final DefaultMQProducer producer;

    /**
     * 延迟消息
     * @param topic         消息topic
     * @param msgContent    消息内容
     * @param delayTime     延迟时间
     */
    public void delayMsg(String topic, String msgContent, Long delayTime) {
        Message msg = new Message(topic, msgContent.getBytes(StandardCharsets.UTF_8));
        msg.putUserProperty(GlobalConstant.DelayConstant.PROPERTY_NAME, String.valueOf(System.currentTimeMillis() + delayTime));
        try {
            producer.send(msg);
            log.info("<----发送延时消息---->消息体:【{}】", JSON.toJSONString(msg));
        } catch (Exception e) {
            log.info("发送延时消息异常，消息体:【{}】，异常信息【{}】", JSON.toJSONString(msg), JSON.toJSONString(e.getMessage()));
        }
    }

    /**
     * 普通消息
     * @param topic         消息topic
     * @param msgContent    消息内容
     */
    public void normalMsg(String topic, String msgContent) {
        Message msg = new Message(topic, msgContent.getBytes(StandardCharsets.UTF_8));
        try {
            producer.send(msg);
            log.info("<----发送普通消息---->消息体:【{}】", JSON.toJSONString(msg));
        } catch (Exception e) {
            log.info("发送普通消息异常，消息体:【{}】，异常信息【{}】", JSON.toJSONString(msg), JSON.toJSONString(e.getMessage()));
        }
    }
}
