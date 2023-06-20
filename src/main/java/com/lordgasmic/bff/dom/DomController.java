package com.lordgasmic.bff.dom;

import com.lordgasmic.bff.collection.CollectionService;
import com.lordgasmic.bff.dom.model.WineNoteOutput;
import com.lordgasmic.bff.session.SessionManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Controller
public class DomController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private SessionManager sessionManager;


    @GetMapping(value = "/dom/v1/hi", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String indexAsDom() {
        return "<html><body>hi</body></html>";
    }

    @GetMapping(value = "/dom/v1/wine/{id}", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String wineAsDom(@PathVariable final String id) {
        final int wineId = Integer.parseInt(id);
        final String username = sessionManager.getSessionDetails().getUsername();
        final CompletableFuture<Map<String, Object>> wineFuture = CompletableFuture.supplyAsync(() -> collectionService.getWines(id, null))
                                                                                   .thenApply(response -> (Map<String, Object>) response);
        final CompletableFuture<String> wineRatingFuture = CompletableFuture.supplyAsync(() -> collectionService.getWineRating(username, wineId))
                                                                            .thenApply((responseList) -> responseList.stream()
                                                                                                                     .map(response -> renderLi(
                                                                                                                             response.formatRating()))
                                                                                                                     .collect(Collectors.joining()));
        final CompletableFuture<String> wineNotesFuture = CompletableFuture.supplyAsync(() -> collectionService.getWineNotes(username, wineId))
                                                                           .thenApply((response) -> response.getWineNotes()
                                                                                                            .stream()
                                                                                                            .sorted(Comparator.comparing(
                                                                                                                    WineNoteOutput::getOrdinal))
                                                                                                            .map(output -> renderLi(output.getNote()))
                                                                                                            .collect(Collectors.joining()));

        final Map<String, Object> wineMap = wineFuture.join();

        final CompletableFuture<Map<String, Object>> wineryFuture = CompletableFuture.supplyAsync(() -> collectionService.getWineryById(((Integer) wineMap.get(
                "wineryId")).toString())).thenApply(response -> (Map<String, Object>) response);

        final String wineRating = wineRatingFuture.join();
        final String wineNotes = wineNotesFuture.join();
        final Map<String, Object> wineryMap = wineryFuture.join();

        String wineHtml = resourceService.getHtml().get("wine");
        wineHtml = StringUtils.replace(wineHtml, "{{wine-name}}", (String) wineMap.get("name"));
        wineHtml = StringUtils.replace(wineHtml, "{{wine-rating}}", wineRating);
        wineHtml = StringUtils.replace(wineHtml, "{{wine-notes}}", wineNotes);

        final String wineHeader = renderWineHeader(wineryMap, wineMap);

        return renderIndex(wineHeader, wineHtml);
    }

    private String renderIndex(final String header, final String body) {
        String html = resourceService.getHtml().get("index");
        html = StringUtils.replace(html, "{{header}}", header);
        html = StringUtils.replace(html, "{{body}}", body);

        return html;
    }

    private String renderWineHeader(final Map<String, Object> winery, final Map<String, Object> wine) {
        String wineHeader = resourceService.getHtml().get("wineHeader");

        final String winerySpan;
        if (winery == null) {
            winerySpan = "";
        } else {
            winerySpan = String.format("<span>&nbsp; > <a href=\"/wineTasting/winery/%s\">%s</a></span>", winery.get("id"), winery.get("name"));
        }

        final String wineSpan;
        if (wine == null) {
            wineSpan = "";
        } else {
            wineSpan = String.format("<span>&nbsp; > <a href=\"/wineTasting/wine/%s\">%s</a></span>", wine.get("id"), wine.get("name"));
        }

        wineHeader = StringUtils.replace(wineHeader, "{{winery}}", winerySpan);
        wineHeader = StringUtils.replace(wineHeader, "{{wine}}", wineSpan);

        return wineHeader;
    }

    private String renderLi(final String data) {
        return StringUtils.replace(resourceService.getHtml().get("li"), "{{data}}", data);
    }
}
