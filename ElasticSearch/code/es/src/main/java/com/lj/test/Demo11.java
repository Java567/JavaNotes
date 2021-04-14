package com.lj.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lj.utils.ESClients;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;

import java.io.IOException;

public class Demo11 {

    ObjectMapper mapper=new ObjectMapper();
    RestHighLevelClient client= ESClients.getClient();
    String index = "sms-logs-index";
    String type = "sms-logs-type";

    @Test
    public void highLightQuery() throws IOException {
        //1. SearchRequest
        SearchRequest request=new SearchRequest(index);
        request.types(type);

        //2. 指定查询条件（高亮）
        SearchSourceBuilder builder=new SearchSourceBuilder();
        //2.1 指定查询条件（高亮）
        builder.query(QueryBuilders.matchQuery("smsContent","盒马"));
        //2.2 指定高亮
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.field("smsContent",10)
                .preTags("<font color='red'>")
                .postTags("</font>");
        builder.highlighter(highlightBuilder);

        request.source(builder);


        //3. 执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        //4. 获取高亮数据输出
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getHighlightFields().get("smsContent"));
        }
    }
}
