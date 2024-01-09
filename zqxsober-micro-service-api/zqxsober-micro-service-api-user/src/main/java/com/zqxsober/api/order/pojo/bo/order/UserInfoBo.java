package com.zqxsober.api.order.pojo.bo.order;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: zqxsober
 * @Description: UserInfoBo 类
 * @Date: 2022-07-19 14:06
 */
@Data
@Accessors(chain = true)
public class UserInfoBo {

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 年龄
     */
    private Long age;
}
