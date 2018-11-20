package com.football.fans.feignclient.client.impl;

import com.football.fans.feignclient.client.PlaceFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HystrixInFeign implements PlaceFeignClient{
    @Override
    public String hello(@RequestParam("name") String name) {
        return null;
    }
}
