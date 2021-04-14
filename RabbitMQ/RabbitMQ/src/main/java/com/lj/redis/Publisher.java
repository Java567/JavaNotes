package com.lj.redis;

import com.lj.config.RabbitMQClient;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

public class Publisher {

    @Test
    public void publish() throws Exception {
        //1.获取Connection
        Connection connection = RabbitMQClient.getConnection();

        //2.创建Channel
        Channel channel = connection.createChannel();


        //3. 发布消息到exchange，同时指定路由的规则
        channel.confirmSelect();

        //开启Return机制
        channel.addReturnListener(new ReturnListener() {
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                //当消息没有到达queue时，才会执信。
                System.out.println(new String(bytes,"UTF-8")+"没有送达到Queue中！！");
            }
        });



        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("消息发送成功，标识："+l+"，是否是批量"+b);
            }

            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("消息发送失败，标识："+l+"，是否是批量"+b);
            }
        });


        AMQP.BasicProperties properties= new AMQP.BasicProperties().builder()
                .deliveryMode(1)  //指定消息是否需要持久化  1- 需要持久化  2- 不需要持久化
                .messageId(UUID.randomUUID().toString())
                .build();
        String msg="Hello-World!!! ";
        channel.basicPublish("","HelloWorld",true,properties,msg.getBytes());




        System.in.read();
        System.out.println("生产者发布消息成功！！！");
        //4.释放资源
        channel.close();
        connection.close();

    }
}
