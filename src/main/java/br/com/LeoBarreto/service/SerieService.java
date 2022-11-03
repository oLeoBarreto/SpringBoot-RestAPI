package br.com.LeoBarreto.service;

import br.com.LeoBarreto.domain.Serie;
import br.com.LeoBarreto.repository.SerieRepository;
import br.com.LeoBarreto.request.SeriePostRequestBody;
import br.com.LeoBarreto.request.SeriePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SerieService{

    private final SerieRepository serieRepository;

    public List<Serie> listAll() {
        return serieRepository.findAll();
    }

    public Serie findByIdOrBadRequestException(Long id) {
        return serieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID not found!"));
    }

    public Serie save(SeriePostRequestBody seriePostRequestBody) {
        return serieRepository.save(Serie.builder().name(seriePostRequestBody.getName()).build());
    }

    public void delete(Long id) {
        serieRepository.delete(findByIdOrBadRequestException(id));
    }

    public void replace(SeriePutRequestBody seriePutRequestBody) {
        Serie existSerie = findByIdOrBadRequestException(seriePutRequestBody.getId());
        Serie serie = Serie.builder()
                .id(existSerie.getId())
                .name(seriePutRequestBody.getName())
                .build();

        serieRepository.save(serie);
    }
}
