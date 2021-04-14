package com.lj.test;

import com.lj.config.RabbitMQClient;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;

public class Demo1 {

    @Test
    public void getConnection() throws IOException {
        Connection connection = RabbitMQClient.getConnection();

        connection.close();
    }
}
