package com.lordgasmic.bffandroid.collection;

import com.lordgasmic.bffandroid.session.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CollectionController {

    private final CollectionService service;

    private final SessionManager sessionManager;

    public CollectionController(final CollectionService service, final SessionManager sessionManager) {
        this.service = service;
        this.sessionManager = sessionManager;
    }

    //    @GetMapping("/api/v1/wineNotes")
    //    public Object getWineNotes(@RequestParam("user") final Optional<String> user, @RequestParam("wineId") final Optional<Integer> wineId) {
    //        return service.getWineNotes(user.orElse(sessionManager.getSessionDetails().getUsername()), wineId.orElse(null));
    //    }

    @GetMapping("/api/v1/bookbujo/{isbn}")
    public Object getBookDataByISBN(@PathVariable final long isbn) {
        return service.getBookDataByISBN(isbn);
    }
}
