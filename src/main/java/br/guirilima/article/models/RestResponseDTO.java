package br.guirilima.article.models;

import lombok.Data;

import java.util.List;

@Data
public class RestResponseDTO {

    private final String message;
    private final int code;
    private final String status;
    private final String objectName;
    private final List<ErrorResponseDTO> errors;
}
