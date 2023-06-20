package com.lordgasmic.bff.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableRedisHttpSession
public class RedisHttpSessionConfiguration {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("redis", 6379));
    }

    @Bean
    @Primary
    public RedisIndexedSessionRepository sessionRepository() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        final RedisIndexedSessionRepository sessionRepository = new RedisIndexedSessionRepository(redisTemplate);
        sessionRepository.setDefaultMaxInactiveInterval(7200);
        sessionRepository.setRedisKeyNamespace("webbff");
        return sessionRepository;
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        final DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("LORDGAMICEWEB");
        serializer.setDomainName("lordgasmic.com");
        serializer.setUseHttpOnlyCookie(true);
        serializer.setUseSecureCookie(true);
        return serializer;
    }
}
