package pe.com.app.user.control.validation.constraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import pe.com.app.user.control.validation.validator.PasswordValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "El password no es valido, no cumple con los requisitos minimos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
