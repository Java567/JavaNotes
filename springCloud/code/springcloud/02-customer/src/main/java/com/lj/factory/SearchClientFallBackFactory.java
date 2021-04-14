package com.lj.factory;

import com.lj.client.SearchClient;
import com.lj.fallback.SearchClientFallBack;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description:
 * @author: LiJun
 * @date: Created in 2021/2/25 15:45
 */
@Component
public class SearchClientFallBackFactory implements FallbackFactory<SearchClient> {

    @Resource
    private SearchClientFallBack searchClientFallBack;

    @Override
    public SearchClient create(Throwable throwable) {
        throwable.printStackTrace();
        return searchClientFallBack;
    }
}
