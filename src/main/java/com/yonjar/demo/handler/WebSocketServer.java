package com.yonjar.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luoyj
 * @date 2021/5/17.
 * @description @ServerEndpoint(value = "/test/oneToMany") 这个地址要和前端调用保持一致
 * @ServerEndpoint(value = "/test/oneToMany") 默认使用String，所以不需要 编码器和解码器
 * 业务场景：分布式锁（redis）+协同编辑(只有一个用户可以编辑，抢到锁和释放锁的时候，通知到其它用户)
 */
@Slf4j
@ServerEndpoint(value = "/test/oneToMany", encoders = TeamworkEditEncoder.class, decoders = TeamworkEditDecoder.class)
@Component
public class WebSocketServer {

    /** 记录当前在线连接数 */
    private static final AtomicInteger onlineCount = new AtomicInteger(0);

    /** 存放所有在线的客户端 */
    private static final Map<String, Session> clients = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        onlineCount.incrementAndGet(); // 在线数加1
        clients.put(session.getId(), session);
        log.info("有新连接加入：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
        //TODO 有客户端连接进入，自动发送消息
        TeamworkEditMessage message = TeamworkEditMessage.builder().appCode("testCode").appVersion("1.0").type(1)
                .domainCode("testDomainCode").groupCode("testGroupCode").pageCode("testPageCode").workflowCode("testWorkflowCode")
                .loginId("123").loginName("测试用户").haveLock(true).expireTime(600).build();

        this.sendMessage(message);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        onlineCount.decrementAndGet(); // 在线数减1
        clients.remove(session.getId());
        log.info("有一连接关闭：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
        //TODO 关闭连接也可以做一些业务操作：页面关闭...
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message
     * 客户端发送过来的消息
     * 当业务改动数据时，可以主动发消息（不需要客户端主动请求）
     */
    @OnMessage
    public void onMessage(TeamworkEditMessage message, Session session) throws IOException, EncodeException {
        log.info("服务端收到客户端[{}]的消息:{}", session.getId(), message);
        //TODO 接收到客户端发的消息 处理业务逻辑
    }
    /*@OnMessage
    public void onMessage(String message, Session session) {
        log.info("服务端收到客户端[{}]的消息:{}", session.getId(), message);
        this.sendMessage(message);
    }*/

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发消息
     * 释放锁
     * @param message
     */
    public void sendMessage(TeamworkEditMessage message) throws IOException, EncodeException {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            Session toSession = sessionEntry.getValue();
            /* 排除掉自己
            if (!fromSession.getId().equals(toSession.getId())) {
                log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
                toSession.getAsyncRemote().sendText(message);
            }*/

            //发送String
//            toSession.getAsyncRemote().sendText(message);

            //发送对象+编码器
            log.info("服务端给所有客户端 发送消息{}",message.toString());
            toSession.getBasicRemote().sendObject(message);
        }
    }

    /**
     * 对特定客户端发送消息
     * 刷新过期时间、抢锁
     * @param message
     * @param toSession
     */
    public void sendMessageToOne(TeamworkEditMessage message, Session toSession) throws IOException, EncodeException {
        log.info("服务端给客户端【{}】 发送消息{}",toSession.getId(),message.toString());
        toSession.getBasicRemote().sendObject(message);
    }
}
