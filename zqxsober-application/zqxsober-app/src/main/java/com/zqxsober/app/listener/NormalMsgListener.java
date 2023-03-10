package com.zqxsober.app.listener;

import com.alibaba.fastjson.JSON;
import com.zqxsober.common.mq.constant.GlobalConstant;
import com.zqxsober.common.mq.constant.MqConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author: zqxsober
 * @Description: UserConsumer 类
 * @Date: 2022-08-05 12:01
 */
@Slf4j
@Component
@RocketMQMessageListener(

        // 消费组，格式：namespace全称%group名称
        consumerGroup = GlobalConstant.Namespace.NAMESPACE + "%" + GlobalConstant.Group.MICRO_SERVER_APP,

        // 需要使用topic全称，所以进行topic名称的拼接，也可以自己设置  格式：namespace全称%topic名称
        topic = GlobalConstant.Namespace.NAMESPACE + "%" + MqConstant.Topic.USER_NORMAL_TOPIC)
public class NormalMsgListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        try {
            log.info("<----普通消费消息---->消息体:【{}】", JSON.toJSONString(message));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("NormalMsgListener 普通消费消息异常：{}--{}", message, e.getMessage());
        }
    }
}
