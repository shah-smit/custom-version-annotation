package com.example.bean.handler;

import com.example.bean.annotation.VersionGetHandler;
import com.example.bean.annotation.VersionHandler;
import org.springframework.stereotype.Component;

@Component
@VersionHandler(version = "1.0")
public class HomeHandler {

    @VersionGetHandler(endpoint = "${bean.get.endpoint}")
    public String getGreeting(){
        return "Greeting from v1.0";
    }

    @VersionGetHandler(endpoint = "${bean.get.endpoint.message}")
    public String giveMeGreeting(String message){
        return message+" v1.0";
    }
}
