package com.zqxsober.app.components;

import com.zqxsober.app.event.AppApplicationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: zqxsober
 * @Description: AppComponent 类
 * @Date: 2023-11-17 13:06
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AppComponent {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void orderAppraiseTask() {
        CompletableFuture.runAsync(this::createEvent)
                .exceptionally(throwable -> {
                    throwable.printStackTrace();
                    log.info("");
                    return null;
                });
    }

    public void createEvent() {
        AppApplicationEvent event = new AppApplicationEvent(this, new Date());
        applicationEventPublisher.publishEvent(event);
    }
}
