package pe.com.app.user.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity()
@Table(name = "DIRECTION")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String citycode;
    private String contrycode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity user;
}
