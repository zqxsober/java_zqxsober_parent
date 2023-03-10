package com.zqxsober.common.mq.constant;

/**
 * @Author: zqxsober
 * @Description: GlobalConstant 接口
 * @Date: 2022-08-05 10:05
 */
public interface GlobalConstant {

    /**
     * 延时消息常量
     */
    interface DelayConstant {

        /**
         * 延时消息参数
         */
        String PROPERTY_NAME = "__STARTDELIVERTIME";
    }

    /**
     * 命名空间
     */
    interface Namespace {

        /**
         * 命名空间
         */
        String NAMESPACE = "rocketmq-w4wopn82a79e|zqxsober";
    }

    /**
     * 组
     */
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
