package com.lj.client;

import com.lj.entity.Customer;
import com.lj.factory.SearchClientFallBackFactory;
import com.lj.fallback.SearchClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: LiJun
 * @date: Created in 2021/2/23 15:46
 */
@FeignClient(value = "SEARCH"
        /*,fallback = SearchClientFallBack.class*/
          ,fallbackFactory = SearchClientFallBackFactory.class)  //指定的是服务名称
public interface SearchClient {

    //value->目标服务的请求路径，method -> 映射请求方式
   @RequestMapping(value = "/search",method = RequestMethod.GET)
    String search();


    @RequestMapping(value = "/search/{id}",method = RequestMethod.GET)
    Customer findById(@PathVariable Integer id);



    @RequestMapping(value = "/getCustomer",method = RequestMethod.GET)
    Customer getCustomer(@RequestParam(value = "id") Integer id,
                         @RequestParam(value = "name") String name);


    //会自动转换位POST请求  405
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    Customer save(@RequestBody Customer customer);


}
