package com.sakshi.poc.conferencedemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    @RequestMapping("/")
    public Map getAppVersion()
    {
        Map map=new HashMap<String,String>();
        map.put("app-version",appVersion);
        return map;
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String handleException()
//    {
//        return "oops something went wrong" ;
//    }

}
