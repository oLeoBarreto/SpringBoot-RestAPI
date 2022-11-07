package br.com.LeoBarreto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SeriePostRequestBody {
    @NotEmpty(message = "The field name cannot be empty")
    private String name;
}
