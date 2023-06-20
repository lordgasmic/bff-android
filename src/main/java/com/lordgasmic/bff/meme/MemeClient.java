package com.lordgasmic.bff.meme;

import com.lordgasmic.bff.meme.model.CreateMemeRequest;
import com.lordgasmic.bff.meme.model.MemeRequestRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "meme-client", url = "${meme-service.url}")
public interface MemeClient {

    @GetMapping(value = "/api/v1/memes/all", headers = {""})
    List<Object> getAllMemes();

    @GetMapping(value = "/api/v1/memes/tag/{tag}", headers = {""})
    List<Object> getMemes(@PathVariable("tag") String tag);

    @PutMapping(value = "/api/v1/meme", headers = {""})
    Object addMeme(CreateMemeRequest request);

    @PutMapping(value = "/api/v1/memes/request")
    Object addMemeRequest(MemeRequestRequest request);
}
