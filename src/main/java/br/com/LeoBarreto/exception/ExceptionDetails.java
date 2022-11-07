package br.com.LeoBarreto.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails { // Classe para definir qual sera p objeto de resposta da Exceptiom
    protected String title;
    protected int status;
    protected String details;
    protected String message;
    protected LocalDateTime timestamp;
}
