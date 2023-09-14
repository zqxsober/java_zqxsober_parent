package com.zqxsober.api.user.api;

import com.zqxsober.api.user.pojo.bo.user.UserInfoBo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: zqxsober
 * @Description: UserApi 类
 * @Date: 2022-07-19 14:04
 */
@FeignClient(value = "zqxsober-micro-service-user", path = "/user")
public interface UserApi {

    /**
     * 获取用户信息
     * @param userId    用户id
     * @return          用户信息
     */
    @GetMapping("/getUserInfo")
    ResponseEntity<UserInfoBo> getUserInfo(@RequestParam("userId") String userId);

}
