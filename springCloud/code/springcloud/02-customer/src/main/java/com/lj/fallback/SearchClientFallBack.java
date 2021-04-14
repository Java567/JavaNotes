package com.lj.fallback;

import com.lj.client.SearchClient;
import com.lj.entity.Customer;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @description:
 * @author: LiJun
 * @date: Created in 2021/2/25 15:33
 */
@Component
public class SearchClientFallBack implements SearchClient {
    @Override
    public String search() {
        return "出现问题了！！！ ";
    }

    @Override
    public Customer findById(Integer id) {
        return null;
    }

    @Override
    public Customer getCustomer(Integer id, String name) {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }
}
