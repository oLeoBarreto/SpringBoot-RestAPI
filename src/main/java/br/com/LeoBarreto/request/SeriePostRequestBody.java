package br.com.LeoBarreto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SeriePostRequestBody {
    @NotEmpty(message = "The field name cannot be empty")
    @Schema(description = "This is the serie title", example = "Stranger Things")
    private String name;
}
