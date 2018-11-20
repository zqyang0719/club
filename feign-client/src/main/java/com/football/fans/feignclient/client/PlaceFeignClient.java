package com.football.fans.feignclient.client;

import com.football.fans.feignclient.client.impl.HystrixInFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * FeignClient的value必须与被访问的服务的application-name一致
 * 方法中添加参数映射
 */
@FeignClient(value = "place-service",fallback = HystrixInFeign.class)
public interface PlaceFeignClient {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);
}
