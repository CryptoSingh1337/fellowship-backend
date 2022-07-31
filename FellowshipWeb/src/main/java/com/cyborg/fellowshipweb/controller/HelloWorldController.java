package com.cyborg.fellowshipweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saranshk04
 */
@RestController
@RequestMapping("/api/v1/hello-world")
public class HelloWorldController {

    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }
}
