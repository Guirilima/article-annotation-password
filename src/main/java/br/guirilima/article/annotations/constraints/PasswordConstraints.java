package br.guirilima.article.annotations.constraints;

import br.guirilima.article.annotations.PasswordValid;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraints implements ConstraintValidator<PasswordValid, String> {

    @Override
    public void initialize(PasswordValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        /* Se conter espaços em branco */
        if (StringUtils.containsWhitespace(value)) return false; //"A senha não deve conter espaços em branco."

        for(char pass : value.toCharArray()) {
            /* Contém palavras repetidas */
            if (StringUtils.countMatches(value, pass) > 1) return false ; //"A senha não deve conter caracteres repetidos"
        }
        return true;
    }
}
