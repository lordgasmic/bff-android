package com.lordgasmic.bffandroid.collection;

import org.springframework.stereotype.Service;

@Service
public class CollectionService {

    private final CollectionClient client;

    public CollectionService(final CollectionClient client) {
        this.client = client;
    }

    public Object getBookDataByISBN(long isbn) {
        return client.getBookDataByISBN(isbn);
    }
}
