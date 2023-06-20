package com.lordgasmic.bffandroid.session.model;

import com.lordgasmic.bffandroid.login.model.LoginResponse;

public class SessionDetailsMapper {
    public static SessionDetails fromLoginResponse(final LoginResponse response) {
        return SessionDetails.builder().roles(response.getRoles()).username(response.getUsername()).build();
    }
}
