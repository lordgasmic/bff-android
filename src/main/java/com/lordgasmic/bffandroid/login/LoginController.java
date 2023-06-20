package com.lordgasmic.bff.login;

import com.lordgasmic.bff.login.model.LoginRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class LoginController {

    private final LoginService service;

    public LoginController(final LoginService service) {
        this.service = service;
    }

    @PostMapping("/api/v1/login")
    public Object login(@RequestBody final LoginRequest request) {
        return service.login(request);
    }

    @GetMapping("/api/v1/logout")
    public void logout() {
        service.logout();
    }

    @GetMapping("/api/v1/users")
    public Object getUsersById(@RequestParam("role") final int role) {
        return service.getUsersByRole(role);
    }
}
