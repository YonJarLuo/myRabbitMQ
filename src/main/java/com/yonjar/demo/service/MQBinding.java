package com.yonjar.demo.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author luoyj
 * @Date 2021/3/31.
 * 使用 spring-cloud-stream 集成 RabbitMQ
 * 非RabbitMQ机器启动本项目，需要 创建另一个账户，并赋予读写权限
 * guest用户只适用local连接
 * 账户没有权限报错：An unexpected connection driver error occured (Exception message: Socket closed)
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
