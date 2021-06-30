package com.yonjar.demo.client;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by LuoYJ on 2019/3/12.
 * 创建消费者
 * 以下程序会一直阻塞，一旦接收到服务器发来的消息就会进行处理
 */
@Slf4j
public class Receive {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(15672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queueName = "hello";
        channel.queueDeclare(queueName,false,false,false,null);

        //消费者basicConsume(队列名称，是否自动ack，consumer)
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);

                //做业务逻辑操作
                //如果设置了手动返回ack  basicAck(获取消息的唯一id，是否批量响应ack)
                channel.basicAck(envelope.getDeliveryTag(),true);

                String message = new String(body,"UTF-8");
                System.out.println("接收的消息："+message);

                if (!envelope.isRedeliver()){
                    log.warn("首次消费消息" + envelope.getDeliveryTag() + "不成功，尝试重试");
                    //id,是否批量响应ack,是否重新投递到队列
                    channel.basicNack(envelope.getDeliveryTag(),true,true);
                }else {
                    log.warn("第二次消费消息" + envelope.getDeliveryTag() + "不成功，丢到死信队列 DLX");
                    channel.basicNack(envelope.getDeliveryTag(),true,false);
                }
            }
        });
    }
}
