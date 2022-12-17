package br.com.LeoBarreto.controller;

import br.com.LeoBarreto.domain.Serie;
import br.com.LeoBarreto.exception.ExceptionDetails;
import br.com.LeoBarreto.request.SeriePostRequestBody;
import br.com.LeoBarreto.request.SeriePutRequestBody;
import br.com.LeoBarreto.service.SerieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("series")
@RequiredArgsConstructor
public class SerieController {
    private final SerieService serieService;

    @Operation(summary = "List all series paginated", description = "The default size is 20, use the parameter size to change the page value", tags = {"series"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @GetMapping
    public ResponseEntity<Page<Serie>> list(@ParameterObject Pageable pageable) {
        return new ResponseEntity<>(serieService.listAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Find a serie by your ID", description = "The ID parameter expect a number", tags = {"series"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Serie.class))}),
            @ApiResponse(responseCode = "400", description = "When not found the ID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class)))
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Serie> findById(@PathVariable Long id) {
        return new ResponseEntity<>(serieService.findByIdOrBadRequestException(id), HttpStatus.FOUND);
    }

    @Operation(summary = "Fnd series by your name", description = "Use the parameter name in the URL to list the series by the name", tags = {"series"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Serie.class)) })
    })
    @GetMapping(path = "/find")
    public ResponseEntity<List<Serie>> findByName(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(serieService.findByName(name), HttpStatus.FOUND);
    }

    @Operation(summary = "Save a new serie", description = "Create and save a new serie in database", tags = {"series"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Serie.class)) })
    })
    @PostMapping(path = "/admin")
    public ResponseEntity<Serie> save(@RequestBody @Valid SeriePostRequestBody seriePostRequestBody) {
        return new ResponseEntity<>(serieService.save(seriePostRequestBody), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a serie", description = "Delete a serie saved in database by your ID", tags = {"series"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "When not found the serie", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class)))
    })
    @DeleteMapping(path = "/admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Update a saved serie", description = "Can update the serie data, but the ID cannot be changed", tags = {"series"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content }),
            @ApiResponse(responseCode = "400", description = "When not found the serie", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class)))
    })
    @PutMapping(path = "/admin")
    public ResponseEntity<Void> replace(@RequestBody SeriePutRequestBody seriePutRequestBody) {
        serieService.replace(seriePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
