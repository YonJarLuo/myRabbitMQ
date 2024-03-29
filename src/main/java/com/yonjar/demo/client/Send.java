package com.yonjar.demo.client;

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
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(15672);
        Connection connection = factory.newConnection();

        //建立通道channel
        Channel channel = connection.createChannel();

        //声明队列
        String queueName = "hello";
        //String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        channel.queueDeclare(queueName,false,false,false,null);
        String message = "Hello RabbitMQ!";

        /**
         * 当前的默认的exchange类型为direct exchange（exchange为""时，使用的是direct exchange，必须要按照指定routing key = queue name匹配的路由方式才能发送
         */
        //进行消息发布
        //exchange  routing key  props  body
        channel.basicPublish("",queueName,null,message.getBytes());

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
