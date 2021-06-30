package com.yonjar.demo.client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
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

        //开启确认 confirm:成功发送消息到broker 后，会返回ack;生产者可以开启监听，做一些其它逻辑操作
        channel.confirmSelect();

        //开启事务，性能慢，不推荐
        /*channel.txSelect();
        channel.txCommit();
        channel.txRollback();*/

        //声明队列queueDeclare(队列，是否持久化，是否排它 只有本连接可以消费，是否自动删除，map里面可以放参数：死信队列)
        String queueName = "hello";
        channel.queueDeclare(queueName,true,false,false,null);
        String message = "Hello RabbitMQ!";

        //声明交换器exchangeDeclare(交换器名称，类型，是否持久化)；如果不声明，有默认的交换器和类型
        channel.exchangeDeclare("","",true);

        //绑定消息routing key 、交换器和队列 关系exchangeBind(目标，源，路由key,其它参数)
        channel.exchangeBind("","","",null);

        //进行消息发布
        //exchange  routing key  props  body
        channel.basicPublish("",queueName,null,message.getBytes());

        //设置监听，当消息成功发送到broker时，则会返回
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {

            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {

            }
        });

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
