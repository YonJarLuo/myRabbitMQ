package com.yonjar.demo.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author luoyj
 * @Date 2021/3/31.
 * 使用 spring-cloud-stream 集成 RabbitMQ
 */
public interface MQBinding {

    String MYSELF_TEST_MESSAGE = "myself-test";

    /**
     * 生产者通道绑定
     * @return
     */
    @Output(MYSELF_TEST_MESSAGE)
    MessageChannel output();

    /**
     * 消费者通道
     * @return
     */
    @Input(MYSELF_TEST_MESSAGE)
    SubscribableChannel input();
}
