package com.lj.springbootrabbitmq.listen;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class Consumer {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RabbitListener(queues = "boot-queue")
    public void getMessage(String msg, Channel channel, Message message) throws IOException {
        System.out.println(redisTemplate.toString());
        //0. 获取MessageId
        String messageId=message.getMessageProperties().getHeader("spring_returned_message_correlation");
        //1. 设置key到Redis
        if(redisTemplate.opsForValue().setIfAbsent(messageId,"0",10, TimeUnit.SECONDS)){
            System.out.println(redisTemplate.opsForValue().setIfAbsent(messageId,"0",10, TimeUnit.SECONDS));
            //2. 消费消息
            System.out.println("接收到消息：" + msg);

            //3. 设置key的value为1
            redisTemplate.opsForValue().set(messageId,"1",10,TimeUnit.SECONDS);
            //4. 手动ack
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }
        else {
            //5. 获取Redis中的value即可，如果是1，手动ack
            if("1".equalsIgnoreCase(redisTemplate.opsForValue().get(messageId))){
                //手动Ack
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }
        }
    }
}
