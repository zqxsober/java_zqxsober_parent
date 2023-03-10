package com.zqxsober.common.mq.emums;

import com.zqxsober.common.mq.constant.MqConstant;
import lombok.AllArgsConstructor;

/**
 * @Author: zqxsober
 * @Description: MqTopicEnum 类
 * @Date: 2022-08-05 12:01
 */
@AllArgsConstructor
public enum MqTopicEnum {

    /**
     * 添加用户积分
     */
    USER_ADD_POINT(MqConstant.Topic.USER_NORMAL_TOPIC, MqConstant.Group.MICRO_SERVER_USER, 0),

    ;

    /**
     * topic
     */
    public final String topic;

    /**
     * 组
     */
    public final String group;

    /**
     * 消息类型，0-普通消息，1延时消息
     */
    public final Integer type;

}
