package pe.com.app.user.control.validation.validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.com.app.user.control.validation.constraint.PasswordConstraint;
import pe.com.app.user.util.ConstProperties;

@Component
public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    @Autowired
    private ConstProperties constProperties;

    private String passwordRegex;

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        passwordRegex = constProperties.getPasswordRegex();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false; // Or true depending on your null handling requirement
        }
        return password.matches(passwordRegex);
    }
}