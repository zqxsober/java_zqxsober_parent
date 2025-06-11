package com.zqxsober.micro.service.user.feign;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.zqxsober.api.order.api.UserApi;
import com.zqxsober.api.order.pojo.bo.order.UserInfoBo;
import com.zqxsober.micro.service.user.pojo.po.UserBase;
import com.zqxsober.micro.service.user.service.UserBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Author: zqxsober
 * @Description: UserFeignImpl 类
 * @Date: 2022-01-04 17:18
 */
@RefreshScope
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserFeignImpl implements UserApi {

    private final UserBaseService userBaseService;


    @Override
    public ResponseEntity<UserInfoBo> getUserInfo(String userId) {
        UserBase userBase = userBaseService.getById(userId);
        if (Objects.isNull(userBase)) {
            return ResponseEntity.notFound().build();
        }
        UserInfoBo body = BeanUtil.copyProperties(userBase, UserInfoBo.class);
        body.setAge(DateUtil.betweenYear(userBase.getBirthday(), DateUtil.date(), true));
        return ResponseEntity.ok(body);
    }
}
