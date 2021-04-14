package com.lj.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lj.utils.ESClients;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Demo13 {

    ObjectMapper mapper=new ObjectMapper();
    RestHighLevelClient client= ESClients.getClient();
    String index = "map";
    String type = "map";

    @Test
    public void geoPolygon() throws IOException {
        //1. 创建SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);

        //2. 指定使用的聚合查询方式
        SearchSourceBuilder builder=new SearchSourceBuilder();
        //--------------------------------------------
        List<GeoPoint> points=new ArrayList<GeoPoint>();
        points.add(new GeoPoint(39.99878,116.298916));
        points.add(new GeoPoint(39.972576,116.29561));
        points.add(new GeoPoint(39.984739,116.327661));
        builder.query(QueryBuilders.geoPolygonQuery("location",points));
        //--------------------------------------------

        request.source(builder);

        //3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        //4. 获取返回结果
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}
