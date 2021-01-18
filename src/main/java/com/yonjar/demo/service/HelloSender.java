package com.yonjar.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class HelloSender {

    @Autowired
    public AmqpTemplate amqpTemplate;

    public void send(String context){
        log.info("Sender:{}",context);
        //hello --> routingKey
        this.amqpTemplate.convertAndSend("hello",context);
    }

}
