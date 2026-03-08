package com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.input.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing")
public class HelloWorldController {

    @GetMapping
    public String HelloWorld(){
        return "Hello World";
    }
}
