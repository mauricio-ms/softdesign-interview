package br.com.softdesign.interview.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    @RequestMapping("/hello-world")
    @GetMapping
    public String helloWorld() {
        return "Hello World!";
    }
}
