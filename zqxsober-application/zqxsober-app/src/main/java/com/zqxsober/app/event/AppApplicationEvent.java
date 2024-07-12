package com.zqxsober.app.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * @Author: zqxsober
 * @Description: AppApplicationEvent 类
 * @Date: 2023-11-17 11:33
 */

@Getter
public class AppApplicationEvent extends ApplicationEvent {


    private final Date now;

    public <T> AppApplicationEvent(Object source, Date date) {
        super(source);
        this.now = date;
    }
}
