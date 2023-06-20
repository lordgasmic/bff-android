package com.lordgasmic.bff.dom;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ResourceService {

    @Autowired
    private ResourceLoader resourceLoader;

    private static final Map<String, String> classpaths = new HashMap<>();

    static {
        classpaths.put("index", "classpath:dom/index.html");
        classpaths.put("wine", "classpath:dom/wine.html");
        classpaths.put("wineHeader", "classpath:dom/wine-header.html");
        classpaths.put("li", "classpath:dom/li.elm");
    }

    @Getter
    private final Map<String, String> html;

    public ResourceService() {
        html = new HashMap<>();
    }

    @PostConstruct
    public void init() throws IOException {
        for (final String path : classpaths.keySet()) {
            final Resource resource = resourceLoader.getResource(classpaths.get(path));
            final BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            final StringBuilder sb = new StringBuilder();
            br.lines().forEach(sb::append);
            br.close();
            html.put(path, sb.toString());
        }
    }
}
