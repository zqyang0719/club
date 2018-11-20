package com.football.fans.feignclient.controller;

import com.football.fans.feignclient.client.PlaceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaceController {

    @Autowired
    public PlaceFeignClient placeFeignClient;

    @RequestMapping(value = "/hello",method= RequestMethod.GET)
    public String hello(@RequestParam("name") String name){
        return placeFeignClient.hello(name);
    }
}
