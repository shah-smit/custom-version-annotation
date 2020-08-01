package com.example.bean.controller;

import com.example.bean.annotation.LogExecutionTime;
import com.example.bean.annotation.version.VersionHandlerController;
import com.example.bean.model.GreetingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    VersionHandlerController versionController;

    @LogExecutionTime(name = "getGreeting")
    @GetMapping("${bean.get.endpoint}")
    public ResponseEntity getGreeting(@PathVariable String version){
        return versionController.findVersionHandler("${bean.get.endpoint}", version, HttpMethod.GET);
    }

    @LogExecutionTime
    @GetMapping("${bean.get.endpoint.message.secondmessage}")
    public ResponseEntity getGreetingWithDoubleMessage(@PathVariable String version, @PathVariable String message, @PathVariable String secondmessage){
        return versionController.findVersionHandler("${bean.get.endpoint.message.secondmessage}", version, HttpMethod.GET, message, secondmessage);
    }

    @LogExecutionTime
    @GetMapping("${bean.get.endpoint.message}")
    public ResponseEntity getGreeting(@PathVariable String version, @PathVariable String message) {
        return versionController.findVersionHandler("${bean.get.endpoint.message}", version, HttpMethod.GET, message);
    }

    @LogExecutionTime
    @PostMapping("${bean.post.endpoint.message}")
    public ResponseEntity getGreeting(@PathVariable String version, @RequestBody GreetingRequest greetingRequest) {
        return versionController.findVersionHandler("${bean.post.endpoint.message}", version, HttpMethod.POST, greetingRequest.getMessage());
    }
}
