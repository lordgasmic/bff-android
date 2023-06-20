package com.lordgasmic.bff.hello;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "hello-world-client", url = "${hello-world-service.url}")
public interface HelloWorldClient {

    @GetMapping(value = "/api/v1/hello/world", headers = {""})
    Object getHello();
}
