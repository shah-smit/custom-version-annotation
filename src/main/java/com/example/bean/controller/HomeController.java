package com.example.bean.controller;

import com.example.bean.annotation.VersionHandlerController;
import com.example.bean.model.GreetingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
public class HomeController {

    @Autowired
    VersionHandlerController versionController;

    @GetMapping("${bean.get.endpoint}")
    public ResponseEntity getGreeting(@PathVariable String version){
        return versionController.findVersionHandler("${bean.get.endpoint}", version, HttpMethod.GET);
    }

    @GetMapping("${bean.get.endpoint.message}")
    public ResponseEntity getGreeting(@PathVariable String version, @PathVariable String message) {
        return versionController.findVersionHandler("${bean.get.endpoint.message}", version, HttpMethod.GET, message);
    }

    @PostMapping("${bean.post.endpoint.message}")
    public ResponseEntity getGreeting(@PathVariable String version, @RequestBody GreetingRequest greetingRequest) {
        return versionController.findVersionHandler("${bean.post.endpoint.message}", version, HttpMethod.POST, greetingRequest.getMessage());
    }
}
