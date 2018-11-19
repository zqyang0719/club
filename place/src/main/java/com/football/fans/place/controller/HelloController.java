package com.football.fans.place.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${hello.word}")
    public String words;

    @RequestMapping("/hello")
    public String hello(@RequestParam String name){
        return words+","+name;
    }
}
