package br.com.LeoBarreto.client;

import br.com.LeoBarreto.domain.Serie;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Serie> entity = new RestTemplate().getForEntity("http://localhost:8080/series/1", Serie.class);
       log.info(entity);

        Serie object = new RestTemplate().getForObject("http://localhost:8080/series/{id}", Serie.class, 1);
        log.info(object);

        Serie dark = Serie.builder().name("Dark").build();
        Serie darkSaved = new RestTemplate().postForObject("http://localhost:8080/series", dark, Serie.class);
        log.info("Saved serie: {}", darkSaved);

        darkSaved.setName("Dark Test");
        ResponseEntity<Void> update = new RestTemplate().exchange("http://localhost:8080/series",
                HttpMethod.PUT,
                new HttpEntity<>(darkSaved, createJsonHeader()),
                Void.class);
        log.info(update);

        ResponseEntity<Void> delete = new RestTemplate().exchange("http://localhost:8080/series/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                darkSaved.getId());
        log.info(delete);
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
