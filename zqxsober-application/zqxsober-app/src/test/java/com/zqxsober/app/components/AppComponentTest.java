package com.zqxsober.app.components;

import com.zqxsober.app.AppApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppApplication.class)
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
class AppComponentTest {

    @Resource
    private AppComponent appComponent;

    @Test
    void orderAppraiseTask() {
        appComponent.orderAppraiseTask();
    }
}