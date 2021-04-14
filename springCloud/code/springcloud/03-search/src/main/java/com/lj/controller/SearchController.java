package com.lj.controller;

import com.lj.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: LiJun
 * @date: Created in 2021/2/22 16:39
 */
@RestController
public class SearchController {


    @Value("${server.port}")
    private String port;

    @GetMapping("/search")
    public String search(){
        int i=1/0;
        return "search"+port;
    }



    @GetMapping("/search/{id}")
    public Customer findById(@PathVariable Integer id){
        return new Customer(1,"张三",(int) (Math.random()*100000));
    }



    @GetMapping("/getCustomer")  //会自动转换位POST请求  405
    public Customer getCustomer(@RequestParam Integer id,
                                @RequestParam String name){
        return new Customer(id,name, (int) (Math.random()*100000));
    }


    @PostMapping("/save")
    public Customer save(@RequestBody Customer customer){
        return customer;
    }
}
