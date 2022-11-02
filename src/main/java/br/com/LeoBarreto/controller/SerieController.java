package br.com.LeoBarreto.controller;

import br.com.LeoBarreto.domain.Serie;
import br.com.LeoBarreto.service.SerieService;
import br.com.LeoBarreto.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("series")
@Log4j2
@RequiredArgsConstructor
public class SerieController {
    private final DateUtil dateUtil;
    private final SerieService serieService;

    @GetMapping
    public ResponseEntity<List<Serie>> list() {
        return new ResponseEntity<>(serieService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Serie> findById(@PathVariable Long id) {
        return new ResponseEntity<>(serieService.findById(id), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Serie> save(@RequestBody Serie serie) {
        return new ResponseEntity<>(serieService.save(serie), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Serie serie) {
        serieService.replace(serie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
