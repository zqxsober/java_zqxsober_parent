package com.zqxsober.app.listener;

import cn.hutool.core.date.DateUtil;
import com.zqxsober.app.event.AppApplicationEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: zqxsober
 * @Description: AppApplicationEventListener 类
 * @Date: 2023-11-17 13:05
 */
public class AppApplicationEventListener implements ApplicationListener<AppApplicationEvent> {


    @Override
    public void onApplicationEvent(@NotNull AppApplicationEvent event) {
        Date now = event.getNow();
        System.out.println(DateUtil.formatDateTime(now));
    }
}
