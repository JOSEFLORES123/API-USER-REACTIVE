package pe.com.app.user.control.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusRequest {

    @NotNull
    private UUID id;

    @NotNull
    @Schema(description = "status(ACTIVE, INACTIVE)", example = "INACTIVE")
    private String status;
}
