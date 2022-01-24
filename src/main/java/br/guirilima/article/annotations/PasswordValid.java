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
        regexp = "^(?=.*[0-9]).*", message = "{validation.constraints.numeric.message}"
),  @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z]).*", message = "{validation.constraints.upAndLowLetter.message}"
),  @Pattern(
        regexp = "^(?=.*[!@#$%^&*()-+]).*", message = "{validation.constraints.special.message}"
)})
@Target({FIELD}) /* A anotação PasswordValid será atribuída a campos da classe. */
@Retention(RUNTIME) /* Estaremos utilizando a anotação PasswordValid em tempo de execução */
@Constraint(validatedBy = PasswordConstraints.class) /* A classe PasswordConstraints, será a responsável pela validação da anotaçao PasswordValid */
public @interface PasswordValid {

    String message() default "{validation.constraints.default.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "";
}
