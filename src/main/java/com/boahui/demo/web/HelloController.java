package com.boahui.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class HelloController {
    private final Logger logger = Logger.getLogger(getClass().getName());
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        client.getServices().forEach(id -> {
            client.getInstances(id).forEach(instanc -> {
                logger.info("/hello,host:" + instanc.getHost() + ",serivce_id:" + instanc.getServiceId());
            });
        });
        return "Hello World";
    }
}
