package com.lj.test;

import com.lj.utils.ESClients;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

public class Demo1 {

    @Test
    public void testConnect(){
        RestHighLevelClient client = ESClients.getClient();
        System.out.println("Ok!!");
    }
}
