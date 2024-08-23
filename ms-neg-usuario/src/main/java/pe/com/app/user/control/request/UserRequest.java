package pe.com.app.user.control.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.app.user.control.validation.constraint.PasswordConstraint;
import pe.com.app.user.util.ExpRegular;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest implements Serializable {

    @NotNull
    @Schema(description = "User name", example = "jose456")
    private String name;

    @NotNull
    @Pattern(regexp = ExpRegular.EMAIL_PATTERN, message = "email no tiene formato, no es valido, podria ser por ejemplo aaaaaaa@dominio.cl")
    @Schema(description = "User name", example = "aaaaaaa@dominio.cl")
    private String email;

    @NotNull
    @Schema(description = "password", example = "ASDFASDFD3424.56")
    @PasswordConstraint
    private String password;

    @NotNull
    private List<PhoneRequest> phones;
}
