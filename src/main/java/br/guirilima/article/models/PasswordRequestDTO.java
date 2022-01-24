package br.guirilima.article.models;

import br.guirilima.article.annotations.PasswordValid;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PasswordRequestDTO {

    private String login;

    @Size(min = 9, message = "Deve conter no minimo 9 caracteres")
    @PasswordValid
    private String password;

}
