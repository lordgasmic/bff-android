package com.lordgasmic.bffandroid.session;

import com.lordgasmic.bffandroid.configuration.LordgasmicConstants;
import com.lordgasmic.bffandroid.session.model.SessionDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class SessionManager {

    private final HttpServletRequest httpServletRequest;

    public SessionManager(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public SessionDetails getSessionDetails() {
        return (SessionDetails) httpServletRequest.getSession().getAttribute(LordgasmicConstants.SESSION_DETAILS_ATTRIBUTE_NAME);
    }

    public void handleLogin(final SessionDetails sessionDetails) {
        httpServletRequest.getSession().invalidate();
        httpServletRequest.getSession(true);
        httpServletRequest.getSession().setAttribute(LordgasmicConstants.SESSION_DETAILS_ATTRIBUTE_NAME, sessionDetails);
    }

    public void handleLogout() {
        httpServletRequest.getSession().removeAttribute(LordgasmicConstants.SESSION_DETAILS_ATTRIBUTE_NAME);
        httpServletRequest.getSession().invalidate();
        httpServletRequest.getSession(true);
    }

    public void save(final int id) {
        httpServletRequest.getSession().setAttribute("derp", "derp-true:" + id);
    }
}
