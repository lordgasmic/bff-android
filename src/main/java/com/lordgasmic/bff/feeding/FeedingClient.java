package com.lordgasmic.bff.feeding;

import com.lordgasmic.bff.feeding.model.FeedRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "feeding-client", url = "${feeding-service.url}")
public interface FeedingClient {

    @PutMapping(value = "/api/v2/feed", headers = {""})
    Object putFeed(FeedRequest request);

    @PutMapping(value = "/api/v2/feeds", headers = {""})
    Object putFeeds(List<FeedRequest> requests);

    @GetMapping(value = "/api/v1/feeds", headers = {""})
    Object getFeeds();
}
