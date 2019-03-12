package com.yonjar.demo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by LuoYJ on 2019/3/12.
 * 创建消费者
 * 以下程序会一直阻塞，一旦接收到服务器发来的消息就会进行处理
 */
public class Receive {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queueName = "hello";
        channel.queueDeclare(queueName,false,false,false,null);

        //创建消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String message = new String(body,"UTF-8");
                System.out.println("接收的消息："+message);
            }
        };

        channel.basicConsume(queueName,true,consumer);
    }
}
