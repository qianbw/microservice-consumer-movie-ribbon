package com.itmuch.cloud.controller;

import com.itmuch.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id) {
        // http://localhost:7900/simple/
        // VIP virtual IP
        // HAProxy Heartbeat
        return this.restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
    }

    @GetMapping("/test")
    public String test() {
        // 使用了自定义的负载均衡策略（随机）
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-user");
        System.out.println("+++++++++++++++++" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

        // 使用默认的负载均衡策略（轮询）
        ServiceInstance serviceInstance2 = this.loadBalancerClient.choose("microservice-provider-user2");
        System.out.println(serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());

        return "1";
    }
}
