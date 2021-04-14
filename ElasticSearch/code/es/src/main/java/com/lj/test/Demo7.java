package com.lj.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lj.utils.ESClients;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import java.io.IOException;

public class Demo7 {
    ObjectMapper mapper=new ObjectMapper();
    RestHighLevelClient client= ESClients.getClient();
    String index = "sms-logs-index";
    String type = "sms-logs-type";

    @Test
    public void scrollQuery() throws IOException {
        //1. 创建SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);

        //2. 指定scroll信息
        request.scroll(TimeValue.timeValueMinutes(1L));

        //3. 指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.size(4);
        builder.sort("fee", SortOrder.DESC);
        builder.query(QueryBuilders.matchAllQuery());

        request.source(builder);

        //4. 获取返回结果scrollId,source
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        String scrollId = response.getScrollId();
        System.out.println("---------首页-----------");
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }


        while (true){
            //5. 循环 - 创建SearchScrollRequest
            SearchScrollRequest scrollRequest=new SearchScrollRequest(scrollId);


            //6. 指定scrollId的生存时间
            scrollRequest.scroll(TimeValue.timeValueMinutes(1L));


            //7. 执行查询获取返回结果
            SearchResponse scrollResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);


            //8. 判断是否查询到了数据，输出
            SearchHit[] hits = scrollResponse.getHits().getHits();
            if(hits!=null && hits.length > 0){
                System.out.println("---------下一页-----------");
                for (SearchHit hit : hits) {
                    System.out.println(hit.getSourceAsMap());
                }
            }

            else {
                //9. 判断没有查询到数据-退出循环
                System.out.println("---------结束-----------");
                break;
            }

        }

        //10. 创建ClearScrollRequest
        ClearScrollRequest clearScrollRequest=new ClearScrollRequest();


        //11. 指定ScrollId
        clearScrollRequest.addScrollId(scrollId);

        //12. 删除ScrollId
        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);

        //13. 输出结果
        System.out.println("删除scroll："+clearScrollResponse.isSucceeded());

    }
}
