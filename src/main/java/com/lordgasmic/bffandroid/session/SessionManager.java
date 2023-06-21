package com.lordgasmic.bffandroid.session;

import com.lordgasmic.bffandroid.session.model.SessionDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
@Component
public class SessionManager {

    private static final Map<String, SessionDetails> stateMachine = new HashMap<>();
    private static final Map<String, Long> cacheMachine = new HashMap<>();

    public void handleLogin(final SessionDetails sessionDetails) {
        stateMachine.put(sessionDetails.getAuthToken(), sessionDetails);
        cacheMachine.put(sessionDetails.getAuthToken(), System.currentTimeMillis());
    }

    public void handleLogout(SessionDetails sessionDetails) {
        stateMachine.remove(sessionDetails.getAuthToken());
        cacheMachine.remove(sessionDetails.getAuthToken());
    }

    public SessionDetails getSessionDetails(String authHeader) {
        return stateMachine.get(authHeader);
    }

    public void cleanUpSessions() {
        Iterator<Map.Entry<String, Long>> it;
        it = cacheMachine.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Long> entry = it.next();
            if (System.currentTimeMillis() - entry.getValue() > 3_600_000) {
                stateMachine.remove(entry.getKey());
                cacheMachine.remove(entry.getKey());
            }
        }
    }
}
