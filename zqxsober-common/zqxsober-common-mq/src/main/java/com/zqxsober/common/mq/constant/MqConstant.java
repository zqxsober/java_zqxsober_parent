package com.zqxsober.common.mq.constant;

/**
 * @Author: zqxsober
 * @Description: GlobalConstant 接口
 * @Date: 2022-08-05 10:05
 */
public interface MqConstant {

    interface Topic {

        /**
         * 用户普通消息
         */
        String USER_NORMAL_TOPIC = "USER_NORMAL_TOPIC";

        /**
         * 用户延时消息
         */
        String USER_DELAY_TOPIC = "USER_DELAY_TOPIC";
    }

    interface Group {

        /**
         * 用户组
         */
        String MICRO_SERVER_USER = "zqxsober-micro-service-user";

        /**
         * app组
         */
        String MICRO_SERVER_APP = "zqxsober-application-app";
    }

}
