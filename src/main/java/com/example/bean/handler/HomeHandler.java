package com.example.bean.handler;

import com.example.bean.annotation.VersionGetHandler;
import com.example.bean.annotation.VersionHandler;
import com.example.bean.annotation.VersionPostHandler;
import org.springframework.stereotype.Component;

@Component
@VersionHandler(version = "1.0")
public class HomeHandler {

    @VersionGetHandler(endpoint = "${bean.get.endpoint}")
    public String getGreeting(){
        return "Greeting from v1.0";
    }
}
