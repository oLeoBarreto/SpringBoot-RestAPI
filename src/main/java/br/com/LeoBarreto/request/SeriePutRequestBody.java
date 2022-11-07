package br.com.LeoBarreto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SeriePutRequestBody {
    private Long id;
    private String name;
}
