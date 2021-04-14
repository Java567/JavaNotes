package com.lj.controller;

import com.lj.client.SearchClient;
import com.lj.entity.Customer;
import com.lj.service.CustomerService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @description:
 * @author: LiJun
 * @date: Created in 2021/2/22 16:41
 */
@RestController
public class CustomerController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private EurekaClient eurekaClient;

    @Resource
    private SearchClient searchClient;

    @Resource
    private CustomerService customerService;


    @GetMapping("/customer")
    public String customer(){
        System.out.println(Thread.currentThread().getName());
        /*//1. 通过eurekaClient获取到SEARCH服务的信息
        InstanceInfo info = eurekaClient.getNextServerFromEureka("SEARCH", false);

        //2. 获取到访问的地址
        String url = info.getHomePageUrl();
        System.out.println(url);

        //3. 通过restTemplate访问
        String result = restTemplate.getForObject(url + "/search", String.class);

         */



        /*//Robbin时
        String result = restTemplate.getForObject("http://SEARCH/search", String.class);
         */


        String result = searchClient.search();

        //4. 返回
        return result;
    }


    @GetMapping("/customer/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFallBack",
    commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "70"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
    })
    public Customer findById(@PathVariable Integer id) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        if(id==1){
            int i=1/0;
        }
        System.out.println(customerService.findById(id));
        System.out.println(customerService.findById(id));
        customerService.clearFindById(id);
        System.out.println(customerService.findById(id));
        System.out.println(customerService.findById(id));
        return searchClient.findById(id);
    }


    // findById的降级方法，方法的描述和接口一致

    public Customer findByIdFallBack(Integer id){
        return new Customer(-1,"",0);
    }



    @GetMapping("/getCustomer")
    public Customer getCustomer(@RequestParam Integer id,
                                @RequestParam String name){
        return searchClient.getCustomer(id,name);
    }


    @GetMapping("/save")  //会自动转换位POST请求  405
    public Customer save( Customer customer){
        return searchClient.save(customer);
    }



}
