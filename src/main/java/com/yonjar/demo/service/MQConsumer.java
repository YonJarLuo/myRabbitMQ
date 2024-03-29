package com.yonjar.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Author luoyj
 * @Date 2021/3/31.
 * 消费者
 */
@Slf4j
@Component
@EnableBinding(MQBinding.class)
public class MQConsumer {

    @StreamListener(MQBinding.MYSELF_TEST_MESSAGE_INPUT)
    public void consumerAppMQ(Message<String> message) {
        String payload = message.getPayload();
        log.info("【MQBinding debug.app.metadata.update MQ接收内容】：{}", payload);
    }
}
