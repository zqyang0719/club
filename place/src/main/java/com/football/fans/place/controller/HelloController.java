package com.football.fans.place.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${some.words}")
    public String words;

    @RequestMapping("/hello")
    public void hello(@RequestBody String name){
        System.out.println(words+","+name);
    }
}
