package com.lj.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lj.utils.ESClients;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;

public class Demo12 {

    ObjectMapper mapper=new ObjectMapper();
    RestHighLevelClient client= ESClients.getClient();
    String index = "sms-logs-index";
    String type = "sms-logs-type";


    @Test
    public void extendedStatus() throws IOException {
        //1. 创建SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);

        //2. 指定使用的聚合查询方式
        SearchSourceBuilder builder=new SearchSourceBuilder();
        //--------------------------------------------
        builder.aggregation(AggregationBuilders.extendedStats("agg").field("fee"));
        //--------------------------------------------

        request.source(builder);

        //3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        //4. 获取返回结果
        ExtendedStats agg = response.getAggregations().get("agg");
        double max = agg.getMax();
        double min = agg.getMin();
        System.out.println("fee的最大值为："+max+",最大值为："+min);
    }


    @Test
    public void range() throws IOException {
        //1. 创建
        SearchRequest request=new SearchRequest(index);
        request.types(type);

        //2. 指定使用的聚合查询方式
        SearchSourceBuilder builder=new SearchSourceBuilder();
        //--------------------------------------------
        builder.aggregation(AggregationBuilders.range("agg").field("fee")
        .addUnboundedTo(5)
        .addRange(5,10)
        .addUnboundedFrom(10));
        //--------------------------------------------

        request.source(builder);

        //3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        //4. 获取返回结果
        Range agg = response.getAggregations().get("agg");
        for (Range.Bucket bucket : agg.getBuckets()) {
            String key = bucket.getKeyAsString();
            Object from = bucket.getFrom();
            Object to = bucket.getTo();
            long docCount = bucket.getDocCount();
            System.out.println(String.format("key：%s,from：%s,to：%s,docCount：%s",key,from,to,docCount));
        }
    }

    @Test
    public void cardinality() throws IOException {
        //1. 创建
        SearchRequest request=new SearchRequest(index);
        request.types(type);

        //2. 指定使用的聚合查询方式
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.cardinality("agg").field("province"));

        request.source(builder);

        //3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        //4. 获取返回结果
        Cardinality agg = response.getAggregations().get("agg");
        System.out.println(agg.getValue());
    }
}
