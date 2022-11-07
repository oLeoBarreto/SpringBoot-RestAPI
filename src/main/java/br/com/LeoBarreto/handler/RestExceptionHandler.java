package br.com.LeoBarreto.handler;

import br.com.LeoBarreto.exception.BadRequestException;
import br.com.LeoBarreto.exception.BadRequestExceptionDetails;
import br.com.LeoBarreto.exception.ExceptionDetails;
import br.com.LeoBarreto.exception.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class) //Caso o controller tenha algum erro do tipo BAD_REQUEST ira retornar ao usuario uma resposta personalizada!
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException exception) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .title("Bad Request Exception")
                        .details(exception.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(exception.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .title("Bad Request Exception, invalid fields")
                        .details("Check the field(s) error(s)")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(exception .getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title(ex.getCause().getMessage())
                .details(ex.getMessage())
                .status(status.value())
                .message(ex.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity(exceptionDetails, headers, status);
    }
}
