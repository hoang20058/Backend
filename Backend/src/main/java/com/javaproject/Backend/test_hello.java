package com.javaproject.Backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test_hello {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(defaultValue = "Người dùng") String name) {
        return "Chào mày nhé " + name + "!";
    }

}
