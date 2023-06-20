package com.lordgasmic.bff.session;

import com.lordgasmic.bff.configuration.LordgasmicConstants;
import com.lordgasmic.bff.session.model.SessionDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class SessionManager {

    @Autowired
    private HttpServletRequest httpServletRequest;

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
