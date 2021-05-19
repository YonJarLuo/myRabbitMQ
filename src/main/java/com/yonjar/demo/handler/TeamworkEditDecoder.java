package com.yonjar.demo.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @Author luoyj
 * @Date 2021/5/19.
 * 编码器 把前端传到后端的String类型的json数据，转换成对象
 */
@Slf4j
public class TeamworkEditDecoder implements Decoder.Text<TeamworkEditMessage> {

    @Override
    public TeamworkEditMessage decode(String jsonMessage) throws DecodeException {
        return JSONObject.parseObject(jsonMessage, TeamworkEditMessage.class);
    }

    @Override
    public boolean willDecode(String jsonMessage) {
        try {
            log.info("服务端接收到的数据：{}",jsonMessage);
            JSONObject.parseObject(jsonMessage, TeamworkEditMessage.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("客户端发送消息到服务端，数据解析失败！");
            return false;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
