package com.lordgasmic.bffandroid.session;

import com.lordgasmic.bffandroid.session.model.SessionDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SessionController {

    private final SessionManager sessionManager;

    public SessionController(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @GetMapping("/api/v1/session")
    public SessionDetails getSessionInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return sessionManager.getSessionDetails(authHeader);
    }
}
