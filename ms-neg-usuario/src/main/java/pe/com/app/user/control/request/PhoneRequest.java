package pe.com.app.user.control.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneRequest implements Serializable {
    private Long id;
    private String number;
    private String citycode;
    private String contrycode;
}
