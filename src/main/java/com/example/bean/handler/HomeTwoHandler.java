package com.example.bean.handler;

import com.example.bean.annotation.version.VersionGetHandler;
import com.example.bean.annotation.version.VersionHandler;
import com.example.bean.annotation.version.VersionPostHandler;
import org.springframework.stereotype.Component;

@Component
@VersionHandler(version = "1.0")
public class HomeTwoHandler {

    @VersionGetHandler(endpoint = "${bean.get.endpoint.message}")
    public String giveMeGreeting(String message){
        return message+" v1.0";
    }

    @VersionGetHandler(endpoint = "${bean.get.endpoint.message.secondmessage}")
    public String giveMeGreeting(String message, String secondMessage){
        return message+" "+secondMessage+" v1.0";
    }

    @VersionPostHandler(endpoint = "${bean.post.endpoint.message}")
    public String getGreetingWithMessage(String message){
        return giveMeGreeting(message);
    }
}
