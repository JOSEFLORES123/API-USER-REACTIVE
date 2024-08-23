package pe.com.app.user.repositorio;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.app.user.modelo.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepo extends JpaRepository<UserEntity, UUID> {

    @EntityGraph(attributePaths = {"phones"})
    Optional<UserEntity> findById(UUID id);

    Optional<UserEntity> findByEmail(String email);
}
