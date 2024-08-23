package pe.com.app.user.control.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.app.user.control.request.PhoneRequest;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse implements Serializable {
    private UUID id;
    private String email;
    private String created;
    private String modified;
    private String last_login;
    private String token;
    private String isactive;
}
