package com.lj.servlet;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

import javax.servlet.annotation.WebServlet;

/**
 * @description:
 * @author: LiJun
 * @date: Created in 2021/2/25 17:19
 */
@WebServlet("/hystrix.stream")
public class HystrixServlet extends HystrixMetricsStreamServlet {
    public static void main(String[] args) {
        int i=000;
        String s =String.valueOf(i);
        if (s.contains("1")){
            System.out.println("1");
        }else {
            System.out.println("2");
        }
    }
}
