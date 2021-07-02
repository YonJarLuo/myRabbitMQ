package com.yonjar.demo.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author luoyj
 * @Date 2021/7/2.
 */
public interface AppOnlineDebugBinding {
    String APP_ONLINE_DEBUG = "app_online_debug";

    /**
     * 生产者通道绑定
     * @return
     */
    @Output(APP_ONLINE_DEBUG)
    MessageChannel output();

    /**
     * 消费者通道
     * @return
     */
    @Input(APP_ONLINE_DEBUG)
    SubscribableChannel input();
}
