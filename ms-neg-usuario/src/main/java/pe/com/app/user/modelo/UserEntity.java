package pe.com.app.user.modelo;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity()
@Table(name = "USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;

    @Column(name = "NAMES")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(updatable = false, name = "CREATED")
    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "MODIFIED")
    private LocalDateTime modified;

    @Timestamp
    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneEntity> phones;
}
