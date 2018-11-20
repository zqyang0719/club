package com.football.fans.feignclient.client.impl;

import com.football.fans.feignclient.client.PlaceFeignClient;
import org.springframework.stereotype.Component;

@Component
public class HystrixInFeign implements PlaceFeignClient{
    @Override
    public String hello(String name) {
        return null;
    }
}
