package es.luis.canyoningApp.application.rest.controller;

import es.luis.canyoningApp.domain.util.AuthenticatedUserBase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
public class BaseController extends AuthenticatedUserBase {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    void addPaginationHeadersToResponse(Page<?> page) {
        response.addHeader(HttpHeaders.LINK, getLinkHeader(page));
        response.addHeader("X-Page-Number", String.valueOf(page.getNumber()));
        response.addHeader("X-Total-Elements", String.valueOf(page.getTotalElements()));
        response.addHeader("X-Total-Pages", String.valueOf(page.getTotalPages()));
    }

    private String getLinkHeader(Page<?> page) {
        StringBuilder header = new StringBuilder();
        UriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromRequest(request);
        if (page.hasNext()) {
            header.append(formatPageableLinkHeader(uriBuilder, page.nextPageable(), "next")).append(", ");
        }
        if (page.hasPrevious()) {
            header
                    .append(formatPageableLinkHeader(uriBuilder, page.previousPageable(), "prev"))
                    .append(", ");
        }
        header
                .append(formatPageableLinkHeader(uriBuilder, page.previousOrFirstPageable(), "first"))
                .append(", ");
        header
                .append(formatPageableLinkHeader(uriBuilder, page.nextOrLastPageable(), "last"))
                .append(", ");
        header.append(formatPageableLinkHeader(uriBuilder, page.getPageable(), "self"));
        return header.toString();
    }

    private String formatPageableLinkHeader(
            UriComponentsBuilder uriComponentsBuilder, Pageable pageable, String rel) {
        String uriString =
                uriComponentsBuilder
                        .cloneBuilder()
                        .replaceQueryParam("page", pageable.getPageNumber())
                        .toUriString();
        return String.format("<%1$s>; rel=\"%2$s\"", uriString, rel);
    }
}
