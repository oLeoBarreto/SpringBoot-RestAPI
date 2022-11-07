package br.com.LeoBarreto.service;

import br.com.LeoBarreto.domain.Serie;
import br.com.LeoBarreto.exception.BadRequestException;
import br.com.LeoBarreto.mapper.SerieMapper;
import br.com.LeoBarreto.repository.SerieRepository;
import br.com.LeoBarreto.request.SeriePostRequestBody;
import br.com.LeoBarreto.request.SeriePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SerieService{

    private final SerieRepository serieRepository;

    public List<Serie> listAll() {
        return serieRepository.findAll();
    }

    public List<Serie> findByName(String name) {
        return serieRepository.findByName(name);
    }

    public Serie findByIdOrBadRequestException(Long id) {
        return serieRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("ID not found!"));
    }

    @Transactional
    public Serie save(SeriePostRequestBody seriePostRequestBody) {
        return serieRepository.save(SerieMapper.INSTANCE.toSerie(seriePostRequestBody));
    }

    public void delete(Long id) {
        serieRepository.delete(findByIdOrBadRequestException(id));
    }

    public void replace(SeriePutRequestBody seriePutRequestBody) {
        Serie existSerie = findByIdOrBadRequestException(seriePutRequestBody.getId());
        Serie serie = SerieMapper.INSTANCE.toSerie(seriePutRequestBody);
        serie.setId(existSerie.getId());

        serieRepository.save(serie);
    }
}
