package com.example.userService.web;

import com.example.userService.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author abdellah
 **/
@RestController
@RefreshScope
public class ConfigTestController {
    @Value("${global.params.p1}")
    private int p1;
    @Value("${global.params.p2}")
    private int p2;
    @Value("${user.params.a}")
    private int a;
    @Value("${user.params.b}")
    private int b;

    @Autowired
    private GlobalConfig globalConfig;

    @GetMapping("/testConfig")
    public Map<String, Integer> configTest(){
        return Map.of("p1",p1,"p2",p2,"a",a,"b",b);
    }

    @GetMapping("/globalConfig")
    public GlobalConfig globalConfig(){
        return globalConfig;
    }
}
