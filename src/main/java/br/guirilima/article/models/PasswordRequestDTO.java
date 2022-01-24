package br.guirilima.article.models;

import br.guirilima.article.annotations.PasswordValid;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PasswordRequestDTO {

    @Size(max = 20)
    private String login;

    @Size(min = 9, message = "{validation.constraints.size.message}")
    @PasswordValid
    private String password;

}
