package com.lordgasmic.bffandroid;

import com.lordgasmic.bffandroid.configuration.LordgasmicRequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableFeignClients
public class BffAndroidApplication {
    private static final Logger logger = LoggerFactory.getLogger(BffAndroidApplication.class);

    public static void main(final String[] args) {

        SpringApplication.run(BffAndroidApplication.class, args);
    }

    @Bean
    public LordgasmicRequestInterceptor bffRequestInterceptor() {
        return new LordgasmicRequestInterceptor();
    }

    @Bean
    public WebMvcConfigurer adapter() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(final InterceptorRegistry registry) {
                registry.addInterceptor(bffRequestInterceptor());
            }
        };
    }
}
