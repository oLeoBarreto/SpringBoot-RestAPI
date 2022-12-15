package br.com.LeoBarreto.controller;

import br.com.LeoBarreto.domain.Serie;
import br.com.LeoBarreto.request.SeriePostRequestBody;
import br.com.LeoBarreto.request.SeriePutRequestBody;
import br.com.LeoBarreto.service.SerieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("series")
@RequiredArgsConstructor
public class SerieController {
    private final SerieService serieService;

    @GetMapping
    public ResponseEntity<Page<Serie>> list(Pageable pageable) {
        return new ResponseEntity<>(serieService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Serie> findById(@PathVariable Long id) {
        return new ResponseEntity<>(serieService.findByIdOrBadRequestException(id), HttpStatus.FOUND);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Serie>> findByName(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(serieService.findByName(name), HttpStatus.FOUND);
    }

    @PostMapping(path = "/admin")
    public ResponseEntity<Serie> save(@RequestBody @Valid SeriePostRequestBody seriePostRequestBody) {
        return new ResponseEntity<>(serieService.save(seriePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/admin")
    public ResponseEntity<Void> replace(@RequestBody SeriePutRequestBody seriePutRequestBody) {
        serieService.replace(seriePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
