package com.lordgasmic.bff.configuration;

import com.lordgasmic.bff.login.model.Role;
import com.lordgasmic.bff.session.SessionManager;
import com.lordgasmic.bff.session.model.SessionDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        final SessionDetails details = sessionManager.getSessionDetails();
        if (details == null) {
            response.setStatus(401);
            return false;
        }
        log.info("session details {}", details);
        return details.getUsername() != null && Role.hasRole(Role.user, details.getRoles());
    }
}
