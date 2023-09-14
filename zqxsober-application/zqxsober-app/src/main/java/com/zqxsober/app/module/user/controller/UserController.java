package com.zqxsober.app.module.user.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.zqxsober.api.user.api.UserApi;
import com.zqxsober.api.user.pojo.bo.user.UserInfoBo;
import com.zqxsober.app.module.user.pojo.vo.UserInfoVO;
import com.zqxsober.common.redis.constant.RedisKeyConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: zqxsober
 * @Description: UserController 类
 * @Date: 2022-07-19 09:43
 */
@Api(tags = "会员接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserApi userApi;

    private final StringRedisTemplate stringRedisTemplate;


    @GetMapping("/getUser")
    @ApiOperation(value = "获取会员信息", response = UserInfoVO.class)
    public ResponseEntity<UserInfoVO> getUser(@RequestParam String userId){
        ResponseEntity<UserInfoBo> response = userApi.getUserInfo(userId);
        if (response.getStatusCode().isError()) {
            throw new NullPointerException("找不到该会员");
        }
        return ResponseEntity.ok(BeanUtil.copyProperties(response.getBody(), UserInfoVO.class));
    }


    @PostMapping("/signTest")
    @ApiOperation(value = "获取会员信息", response = UserInfoVO.class)
    public ResponseEntity<?> signTest(@RequestParam("userId") String userId, @RequestParam("date") String date) {
        Date today = StrUtil.isBlank(date) ? DateUtil.date() : DateUtil.parseDate(date);
        String key = RedisKeyConstant.SystemConstant.PREFIX_KET_APP + StrUtil.DOT + "sign" + StrUtil.DOT + userId + StrUtil.DOT + DateUtil.format(today, "yyyy-MM");
        int day = DateUtil.dayOfMonth(today);
        Boolean flag = stringRedisTemplate.opsForValue().setBit(key, day, true);

        Long count = stringRedisTemplate.execute((RedisCallback<Long>) redisConnection -> redisConnection.bitCount(key.getBytes()));
        assert count != null;
        System.out.println(count.intValue());
        return ResponseEntity.ok(flag);
    }




}
