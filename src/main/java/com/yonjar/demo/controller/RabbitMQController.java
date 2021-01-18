package com.yonjar.demo.controller;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.yonjar.demo.cmd.MessageCmd;
import com.yonjar.demo.service.HelloSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api("RabbitMQ 一对一")
@RestController
@RequestMapping("/rabbit/one_to_one")
public class RabbitMQController {

    @Autowired
    private HelloSender helloSender;

    @ApiOperation("发送消息")
    @PostMapping("/send")
    public Response send(@RequestBody MessageCmd messageCmd){
        helloSender.send(messageCmd.getContext());
        return SingleResponse.buildSuccess();
    }
}
