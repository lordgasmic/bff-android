package com.lordgasmic.bff.hello;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class HelloWorldController {

    private final HelloWorldService service;

    public HelloWorldController(final HelloWorldService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/hello/world")
    public Object get() {
        System.out.println("got here");
        return service.getHello();
    }
}
