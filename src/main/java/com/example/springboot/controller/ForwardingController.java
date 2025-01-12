package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardingController {
    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        // Forward all requests to the index.html
        return "forward:/index.html";
    }
}
