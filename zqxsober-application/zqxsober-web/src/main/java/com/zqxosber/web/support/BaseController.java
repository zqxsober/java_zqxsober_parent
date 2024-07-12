package com.zqxosber.web.support;

import com.zqxsober.api.order.api.UserApi;
import com.zqxsober.api.order.pojo.bo.order.UserInfoBo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: zqxsober
 * @Description: BaseController 类
 * @Date: 2023-07-04 09:38
 */
@Component
public class BaseController {

    @Resource
    private UserApi userApi;

    public UserInfoBo getUserInfo(String userId) {
        ResponseEntity<UserInfoBo> response = userApi.getUserInfo(userId);
        if (Objects.isNull(response)) {
            return null;
        }
        return response.getBody();
    }
}
