package br.guirilima.article.controllers.adviceControllers;

import br.guirilima.article.models.ErrorResponseDTO;
import br.guirilima.article.models.RestResponseDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorResponseDTO> errors = getErrors(ex);
        RestResponseDTO restResponseDTO = getRestResponseDTO(ex, status, errors);
        return new ResponseEntity<>(restResponseDTO, status);
    }

    private RestResponseDTO getRestResponseDTO(MethodArgumentNotValidException ex, HttpStatus status, List<ErrorResponseDTO> errors) {
        return new RestResponseDTO("Requisição possui campos inválidos", status.value(),
                status.getReasonPhrase(), errors);
    }

    private List<ErrorResponseDTO> getErrors(MethodArgumentNotValidException ex) {

        List<ErrorResponseDTO> errorResponseDTOS = new ArrayList<>();

        ex.getBindingResult().getFieldErrors()
            .stream()
            .collect(Collectors.groupingBy(FieldError::getField))
            .forEach( (key,fieldErro) -> {
                errorResponseDTOS.add(ErrorResponseDTO.builder()
                        .field(key)
                        .messages(fieldErro.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()))
                        .build());
        });

        return errorResponseDTOS;
    }
}
