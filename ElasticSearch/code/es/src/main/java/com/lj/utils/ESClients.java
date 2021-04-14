package com.lj.utils;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ESClients {

    public static RestHighLevelClient getClient(){

        // 创建HttpHost对象
        HttpHost httpHost=new HttpHost("192.168.190.130",9200);

        // 创建clientBuilder
        RestClientBuilder clientBuilder= RestClient.builder(httpHost);

        // 创建RestHighLevelClient
        RestHighLevelClient client=new RestHighLevelClient(clientBuilder);

        //返回
        return client;
    }
}
