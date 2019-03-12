package com.yonjar.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by LuoYJ on 2019/3/12.
 * RabbitMQ需先安装Erlang服务器，再安装运行RabbitMQ  成功之后浏览器访问: http://localhost:15672
 * 当然，在微服务中使用的话，推荐使用Spring Cloud Stream.使用它时，我们不再需要直接调用各个消息代理框架的API，只需要进行简单的绑定，调用SpringCloud Stream封装好的API即可
 * 创建消息生产者并发送消息
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();

        //建立通道channel
        Channel channel = connection.createChannel();

        //声明队列
        String queueName = "hello";
        channel.queueDeclare(queueName,false,false,false,null);
        String message = "Hello RabbitMQ!";

        //进行消息发布
        channel.basicPublish("",queueName,null,message.getBytes());

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
