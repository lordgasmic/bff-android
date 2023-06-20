package com.lordgasmic.bffandroid.collection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "collection-client", url = "${collection-service.url}")
public interface CollectionClient {

    @GetMapping(value = "/api/v1/bookbujo/{isbn}", headers = {""})
    Object getBookDataByISBN(@PathVariable("isbn") long isbn);
}
