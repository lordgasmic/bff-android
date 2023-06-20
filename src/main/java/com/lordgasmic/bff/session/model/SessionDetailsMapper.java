package com.lordgasmic.bff.session.model;

import com.lordgasmic.bff.login.model.LoginResponse;

public class SessionDetailsMapper {
    public static SessionDetails fromLoginResponse(final LoginResponse response) {
        return SessionDetails.builder().roles(response.getRoles()).username(response.getUsername()).build();
    }
}
