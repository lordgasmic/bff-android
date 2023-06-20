package com.lordgasmic.bffandroid.login.model;

import lombok.Data;

@Data
public class LoginResponse {
    private String username;
    private int roles;
    private boolean enabled;
    private boolean credentialsValid;
}
