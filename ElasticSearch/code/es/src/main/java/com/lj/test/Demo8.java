package com.lj.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lj.utils.ESClients;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.junit.Test;

import java.io.IOException;

public class Demo8 {

    ObjectMapper mapper=new ObjectMapper();
    RestHighLevelClient client= ESClients.getClient();
    String index = "sms-logs-index";
    String type = "sms-logs-type";

    @Test
    public void deleteByQuery() throws IOException {
        //1. 创建DeleteByQueryRequest
        DeleteByQueryRequest request=new DeleteByQueryRequest(index);
        request.types(type);

        //2. 指定检索的条件   和SearchRequest指定Query的方式不一样
        request.setQuery(QueryBuilders.rangeQuery("fee").lt(4));

        //3. 执行删除
        BulkByScrollResponse response = client.deleteByQuery(request, RequestOptions.DEFAULT);

        //4. 删除返回结果
        System.out.println(response.toString());
    }
}
