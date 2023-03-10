package com.zqxsober.micro.service.message.service.impl;

import com.zqxsober.micro.service.message.pojo.po.MqMessageLog;
import com.zqxsober.micro.service.message.mapper.MqMessageLogMapper;
import com.zqxsober.micro.service.message.service.MqMessageLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息队列记录表 服务实现类
 * </p>
 *
 * @author zqxsober
 * @since 2022-08-08
 */
@Service
public class MqMessageLogServiceImpl extends ServiceImpl<MqMessageLogMapper, MqMessageLog> implements MqMessageLogService {

}
