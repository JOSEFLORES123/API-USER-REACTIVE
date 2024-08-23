package pe.com.app.user.control.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.app.user.control.validation.constraint.PasswordConstraint;
import pe.com.app.user.util.ExpRegular;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @NotNull
    private UUID id;

    @NotNull
    @Pattern(regexp = ExpRegular.EMAIL_PATTERN, message = "email no tiene formato, no es valido, podria ser por ejemplo aaaaaaa@dominio.cl")
    @Schema(description = "User name", example = "aaaaaaa@dominio.cl")
    private String email;

    @NotNull
    @PasswordConstraint
    private String password;
}
