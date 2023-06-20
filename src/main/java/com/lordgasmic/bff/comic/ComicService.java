package com.lordgasmic.bff.comic;

import org.springframework.stereotype.Service;

@Service
public class ComicService {

    private final ComicClient client;

    public ComicService(final ComicClient client) {
        this.client = client;
    }

    public Object getComic() {
        return client.getComic();
    }
}
