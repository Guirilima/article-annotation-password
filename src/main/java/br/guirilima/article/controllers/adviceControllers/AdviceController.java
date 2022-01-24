package br.guirilima.article.controllers.adviceControllers;

import br.guirilima.article.models.ErrorResponseDTO;
import br.guirilima.article.models.RestResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
    }

    private List<ErrorResponseDTO> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponseDTO(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }
}
