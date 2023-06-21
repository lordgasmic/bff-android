package com.lordgasmic.bffandroid.configuration;

import com.lordgasmic.bffandroid.session.SessionManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SpringConfig {

    private final SessionManager sessionManager;

    public SpringConfig(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Scheduled(cron = "0 * * * * *")
    public void cleanUpSessions() {
        sessionManager.cleanUpSessions();
    }
}
