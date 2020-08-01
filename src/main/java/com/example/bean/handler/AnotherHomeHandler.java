package com.example.bean.handler;

import com.example.bean.annotation.version.VersionGetHandler;
import com.example.bean.annotation.version.VersionHandler;
import com.example.bean.annotation.version.VersionPostHandler;
import org.springframework.stereotype.Component;

@Component
@VersionHandler(version = "2.0")
public class AnotherHomeHandler {

    @VersionGetHandler(endpoint = "${bean.get.endpoint}")
    public String giveMeGreeting(){
        return "Greeting from v2.0";
    }

    @VersionGetHandler(endpoint = "${bean.get.endpoint.message}")
    public String giveMeGreeting(String message){
        return message+" v2.0";
    }

    @VersionPostHandler(endpoint = "${bean.post.endpoint.message}")
    public String getGreetingWithMessage(String message){
        return giveMeGreeting(message);
    }

}
