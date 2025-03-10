package com.zqxsober.micro.service.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqxsober.micro.service.user.pojo.po.UserBase;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zqxsober
 * @since 2022-07-19
 */
public interface UserBaseService extends IService<UserBase> {

    /**
     * 根据id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    UserBase getUserBase(String id);

}
