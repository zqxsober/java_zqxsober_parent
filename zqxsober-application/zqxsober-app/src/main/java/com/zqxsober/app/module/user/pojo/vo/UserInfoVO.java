package com.zqxsober.app.module.user.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: zqxsober
 * @Description: UserInfoVO 类
 * @Date: 2022-07-19 09:48
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class UserInfoVO {

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String userName;

    /**
     * 用户Id
     */
    @ApiModelProperty("用户Id")
    private String userId;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Long age;
}
