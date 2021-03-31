package com.yonjar.demo.controller;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.yonjar.demo.cmd.MessageCmd;
import com.yonjar.demo.service.HelloSender;
import com.yonjar.demo.service.MQBinding;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api("RabbitMQ 一对一")
@Slf4j
@RestController
@RequestMapping("/rabbit/one_to_one")
public class RabbitMQController {

    @Autowired
    private HelloSender helloSender;
    @Autowired
    private MQBinding mqBinding;

    @ApiOperation("发送消息")
    @PostMapping("/send")
    public Response send(@RequestBody MessageCmd messageCmd){
        helloSender.send(messageCmd.getContext());
        return SingleResponse.buildSuccess();
    }

    @ApiOperation("使用SpringCloudStream 发送消息")
    @PostMapping("/send2")
    public Response send2(@RequestBody MessageCmd messageCmd){
        log.info("【MQ发送内容】：{}", messageCmd.getContext());
        mqBinding.output().send(MessageBuilder.withPayload(messageCmd.getContext()).build());
        return SingleResponse.buildSuccess();
    }
}
