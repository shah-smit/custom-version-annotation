package com.example.bean.controller;

import com.example.bean.annotation.VersionHandlerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
