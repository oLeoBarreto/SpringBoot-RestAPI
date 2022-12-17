package br.com.LeoBarreto.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails { // Classe para definir qual sera p objeto de resposta da Exceptiom
    @Schema(description = "Error title", example = "Bad Request Exception")
    protected String title;
    @Schema(description = "Error code status", example = "400")
    protected int status;
    @Schema(description = "Error detail", example = "ID not found!")
    protected String details;
    @Schema(description = "Error message", example = "br.com.LeoBarreto.exception.BadRequestException")
    protected String message;
    @Schema(description = "Error timestamp", example = "2022-12-17T12:43:59.6873204")
    protected LocalDateTime timestamp;
}
