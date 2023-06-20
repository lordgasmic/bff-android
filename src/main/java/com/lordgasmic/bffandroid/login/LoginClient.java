package com.lordgasmic.bff.login;

import com.lordgasmic.bff.login.model.LoginRequest;
import com.lordgasmic.bff.login.model.LoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "login-client", url = "${login-service.url}")
public interface LoginClient {

    @PostMapping(value = "/api/v1/login", headers = {""})
    LoginResponse login(LoginRequest request);

    @GetMapping(value = "/api/v1/users")
    Object getUsersByRole(@RequestParam("role") int role);
}
