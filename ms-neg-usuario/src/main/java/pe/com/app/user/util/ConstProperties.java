package pe.com.app.user.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConstProperties {

    @Value("${validation.regex.user.password}")
    private String passwordRegex;

    public String getPasswordRegex() {
        return passwordRegex;
    }
}
