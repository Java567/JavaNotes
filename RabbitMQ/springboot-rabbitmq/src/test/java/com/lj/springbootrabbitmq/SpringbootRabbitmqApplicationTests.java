package com.lj.springbootrabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.UUID;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() throws IOException {
        CorrelationData messageId=new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("boot-topic-exchange","slow.red.dog","红色大狼狗",messageId);
        System.in.read();
    }

}
