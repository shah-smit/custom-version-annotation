package com.example.bean.handler;

import com.example.bean.annotation.version.VersionGetHandler;
import com.example.bean.annotation.version.VersionHandler;
import org.springframework.stereotype.Component;

@Component
@VersionHandler(version = "1.0")
public class HomeHandler {

    @VersionGetHandler(endpoint = "${bean.get.endpoint}")
    public String getGreeting(){
        return "Greeting from v1.0";
    }
}
