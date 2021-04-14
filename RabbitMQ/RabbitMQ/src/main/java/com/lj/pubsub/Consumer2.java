package com.lj.pubsub;

import com.lj.config.RabbitMQClient;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;

public class Consumer2 {

    @Test
    public void Consume() throws Exception {
        //1. 获取连接对象
        Connection connection = RabbitMQClient.getConnection();

        //2. 创建channel
        final Channel channel = connection.createChannel();

        //3. 声明队列-Word
        channel.queueDeclare("pubsub-queue2",true,false,false,null);

        //3.5 指定当前消费者，一次消费多少个消息
        channel.basicQos(1);


        //4. 开启监听Queue
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者1号接收到消息："+new String(body,"UTF-8"));

                //手动ack
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };

        //参数1：queue - 指定消费哪个队列
        //参数2：autoAck - 指定是否自动ACK(true,接收到消息后，会立即告诉RabbitMQ)
        //参数3：consumer - 指定消费回调
        channel.basicConsume("pubsub-queue2",false,consumer);
        System.out.println("消费者开始监听队列！！！");
        //System.in.read()
        System.in.read();
        //5. 释放资源
        channel.close();
        connection.close();
    }
}
