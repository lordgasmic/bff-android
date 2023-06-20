package com.lordgasmic.bff.login;

import com.lordgasmic.bff.login.model.LoginRequest;
import com.lordgasmic.bff.login.model.LoginResponse;
import com.lordgasmic.bff.session.SessionManager;
import com.lordgasmic.bff.session.model.SessionDetails;
import com.lordgasmic.bff.session.model.SessionDetailsMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginClient client;
    private final SessionManager sessionManager;

    public LoginService(final LoginClient client, final SessionManager sessionManager) {
        this.client = client;
        this.sessionManager = sessionManager;
    }

    public Object login(final LoginRequest request) {
        final LoginResponse response = client.login(request);

        if (response.isCredentialsValid() && response.isEnabled()) {
            final SessionDetails sessionDetails = SessionDetailsMapper.fromLoginResponse(response);
            sessionManager.handleLogin(sessionDetails);
            return sessionDetails;
        }

        return null;
    }

    public void logout() {
        sessionManager.handleLogout();
    }

    public Object getUsersByRole(final int role) {
        return client.getUsersByRole(role);
    }
}
