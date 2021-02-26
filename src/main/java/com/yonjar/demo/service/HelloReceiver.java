package com.yonjar.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*@Slf4j
@Component
public class HelloReceiver {

    //监听器监听指定的Queue  当hello队列中有新消息加入时，立即消费
    @RabbitListener(queues="hello")
    public void process(String context){
        log.info("Receiver:"+context);

    }

}*/
