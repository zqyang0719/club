package com.football.fans.feignclient.client;

import com.football.fans.feignclient.client.impl.HystrixInFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "placeclient",fallback = HystrixInFeign.class)
public interface PlaceFeignClient {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(String name);
}
