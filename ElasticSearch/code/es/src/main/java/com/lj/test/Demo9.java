package com.lj.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lj.utils.ESClients;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.BoostingQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;

public class Demo9 {

    ObjectMapper mapper=new ObjectMapper();
    RestHighLevelClient client= ESClients.getClient();
    String index = "sms-logs-index";
    String type = "sms-logs-type";


    @Test
    public void BoostingQuery() throws IOException {
        //1. 创建SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);

        //2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        BoostingQueryBuilder boostingQuery= QueryBuilders.boostingQuery(
                QueryBuilders.matchQuery("smsContent", "收货安装"),
                QueryBuilders.matchQuery("smsContent", "王五")
        ).negativeBoost(0.5f);

        builder.query(boostingQuery);
        request.source(builder);


        //3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        //4. 输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }


    /*
    # 查询省份为武汉或者北京
    # 运营商不是联通
    # smsContent包含中国和平安
     */
    @Test
    public void BoolQuery() throws IOException {
        //1. 创建SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);

        //2. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // # 查询省份为武汉或者北京
        boolQuery.should(QueryBuilders.termQuery("province","武汉"));
        boolQuery.should(QueryBuilders.termQuery("province","北京"));
        // # 运营商不是联通
        boolQuery.mustNot(QueryBuilders.termQuery("operatorId",2));
        // # smsContent包含中国和平安
        boolQuery.must(QueryBuilders.termQuery("smsContent","中国"));
        boolQuery.must(QueryBuilders.termQuery("smsContent","平安"));

        builder.query(boolQuery);
        request.source(builder);


        //3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        //4. 输出结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}
