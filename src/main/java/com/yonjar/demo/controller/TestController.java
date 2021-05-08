package com.yonjar.demo.controller;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author luoyj
 * @Date 2021/5/8.
 */
@Api("测试拦截器")
@Slf4j
@RestController
@RequestMapping("/api/launcher")
public class TestController {

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public Response test(){
        return SingleResponse.of("test");
    }

    @ApiOperation("测试接口")
    @GetMapping("/test2")
    public Response test2(){
        return SingleResponse.of("test2");
    }

    @ApiOperation("测试接口")
    @GetMapping("/test2/hello")
    public Response test3(){
        return SingleResponse.of("test3");
    }
}
