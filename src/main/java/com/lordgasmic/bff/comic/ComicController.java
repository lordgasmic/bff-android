package com.lordgasmic.bff.comic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ComicController {

    private final ComicService service;

    public ComicController(final ComicService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/comic/derp")
    public Object getComic() {
        return service.getComic();
    }
}
