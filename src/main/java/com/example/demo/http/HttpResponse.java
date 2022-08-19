package com.example.demo.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Controller
@RestController
@RequestMapping
public class HttpResponse {

    @RequestMapping
    public String uoloadResponse(@RequestParam("param")String param){
        System.out.println("http传来请求参数: "+param);
        return "response";
    }

}
