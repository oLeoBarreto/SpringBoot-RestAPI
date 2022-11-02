package br.com.LeoBarreto.service;

import br.com.LeoBarreto.domain.Serie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SerieService{
    private static List<Serie> series;
    static {
        series = new ArrayList<>(List.of(new Serie(1L, "The Walking Dead"), new Serie(2L, "Round 6")));
    }

    public List<Serie> listAll() {
        return series;
    }

    public Serie findById(Long id) {
        return series.stream()
                        .filter(serie -> serie.getId().equals(id))
                        .findFirst()
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID not found!"));
    }

    public Serie save(Serie serie) {
        serie.setId(ThreadLocalRandom.current().nextLong(3, 100000));
        series.add(serie);
        return serie;
    }

    public void delete(Long id) {
        series.remove(findById(id));
    }

    public void replace(Serie serie) {
        delete(serie.getId());
        series.add(serie);
    }
}
