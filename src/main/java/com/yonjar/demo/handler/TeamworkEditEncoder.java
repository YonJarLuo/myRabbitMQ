package com.yonjar.demo.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @Author luoyj
 * @Date 2021/5/18.
 * 解码器 把对象转换成 json格式的String类型返回给前端
 */
@Slf4j
public class TeamworkEditEncoder implements Encoder.Text<TeamworkEditMessage> {
    @Override
    public String encode(TeamworkEditMessage teamworkEditMessage) throws EncodeException {
        try {
            return JSON.toJSONString(teamworkEditMessage);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("服务端数据转换json结构失败！");
            return "";
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
