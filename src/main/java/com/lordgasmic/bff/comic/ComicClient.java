package com.lordgasmic.bff.comic;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "comic-client", url = "${comic-service.url}")
public interface ComicClient {

    @GetMapping(value = "/api/v1/comic/derp", headers = {""})
    Object getComic();
}
