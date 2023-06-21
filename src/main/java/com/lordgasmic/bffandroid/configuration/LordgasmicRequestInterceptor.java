package com.lordgasmic.bffandroid.configuration;

import com.lordgasmic.bffandroid.login.model.Role;
import com.lordgasmic.bffandroid.session.SessionManager;
import com.lordgasmic.bffandroid.session.model.SessionDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lordgasmic.bffandroid.configuration.LordgasmicConstants.BOOK_BU_JO_ROLE;

@Slf4j
public class LordgasmicRequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SessionManager sessionManager;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        log.info("starting preHandle");
        if (request.getServletPath().contains("api/v1/login")) {
            log.info("login handler found");
            return true;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            response.setStatus(400);
            return false;
        }

        final SessionDetails details = sessionManager.getSessionDetails(authHeader);
        if (details == null) {
            response.setStatus(401);
            return false;
        }
        log.info("session details {}", details);

        if (request.getServletPath().contains("bookbujo") && (BOOK_BU_JO_ROLE & details.getRoles()) != BOOK_BU_JO_ROLE) {
            response.setStatus(403);
            return false;
        }
        return details.getUsername() != null && Role.hasRole(Role.user, details.getRoles());

    }
}
