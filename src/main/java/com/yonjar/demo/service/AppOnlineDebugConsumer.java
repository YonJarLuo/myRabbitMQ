package com.yonjar.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Author luoyj
 * @Date 2021/7/2.
 */
@Slf4j
@Component
@EnableBinding(AppOnlineDebugBinding.class)
public class AppOnlineDebugConsumer {

    @StreamListener(AppOnlineDebugBinding.APP_ONLINE_DEBUG)
    public void consumerAppMQ(Message<String> message) {
        String payload = message.getPayload();
        log.info("【AppOnlineDebugConsumer MQ接收内容】：{}", payload);
    }
}
