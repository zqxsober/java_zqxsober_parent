package com.zqxsober.micro.service.user.service.impl;

import com.zqxsober.micro.service.user.pojo.po.UserBase;
import com.zqxsober.micro.service.user.mapper.UserBaseMapper;
import com.zqxsober.micro.service.user.service.UserBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zqxsober
 * @since 2022-07-19
 */
@Service
@RequiredArgsConstructor
public class UserBaseServiceImpl extends ServiceImpl<UserBaseMapper, UserBase> implements UserBaseService {

    private final UserBaseMapper userBaseMapper;

    @Override
    public UserBase getUserBase(String id) {
        return this.getById(id);
    }
}
