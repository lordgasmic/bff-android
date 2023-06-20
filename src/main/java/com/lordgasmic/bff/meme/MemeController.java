package com.lordgasmic.bff.meme;

import com.lordgasmic.bff.meme.model.CreateMemeRequest;
import com.lordgasmic.bff.meme.model.MemeRequestRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Slf4j
public class MemeController {

    private final MemeService service;

    public MemeController(final MemeService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/memes/all")
    public Object getAllMemes() {
        return service.getAllMemes();
    }

    @GetMapping("/api/v1/memes/tag/{tag}")
    public Object getMemes(@PathVariable final String tag) {
        log.info("staring memes by tag");
        return service.getMemes(tag);
    }

    @PutMapping("/api/v1/meme")
    public void addMeme(@RequestBody final CreateMemeRequest request) {
        service.addMeme(request);
    }

    @PutMapping(value = "/api/v1/memes/request")
    public void addMemeRequest(@RequestBody final MemeRequestRequest request) {
        service.addMemeRequest(request);
    }
}
