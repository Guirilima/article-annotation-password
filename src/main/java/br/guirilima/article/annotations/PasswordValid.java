package br.guirilima.article.annotations;

import br.guirilima.article.annotations.constraints.PasswordConstraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@javax.validation.constraints.Pattern.List({
    @Pattern(
        regexp = "^(?=.*[0-9]).*", message = "Deve conter pelo menos um caractere númerico"
),  @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z]).*", message = "Deve conter pelo menos uma letra Minuscula e uma Maiuscula"
),  @Pattern(
        regexp = "^(?=.*[!@#$%^&*()-+]).*", message = "Deve conter pelo menos um caractere especial"
)})
@Target({FIELD}) /* A anotação PasswordValid será atribuída a campos da classe. */
@Retention(RUNTIME) /* Estaremos utilizando a anotação PasswordValid em tempo de execução */
@Constraint(validatedBy = PasswordConstraints.class) /* A classe PasswordConstraints, será a responsável pela validação da anotaçao PasswordValid */
public @interface PasswordValid {

    String message() default "A senha não pode conter espaços em branco ou caracteres repetidos.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "";
}
