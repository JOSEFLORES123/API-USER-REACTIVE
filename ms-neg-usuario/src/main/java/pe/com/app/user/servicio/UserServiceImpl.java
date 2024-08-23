package pe.com.app.user.servicio;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.app.user.control.request.UserRequest;
import pe.com.app.user.control.request.UserStatusRequest;
import pe.com.app.user.control.request.UserUpdateRequest;
import pe.com.app.user.control.response.UserDetailResponse;
import pe.com.app.user.control.response.UserResponse;
import pe.com.app.user.exception.BusinessException;
import pe.com.app.user.modelo.UserEntity;
import pe.com.app.user.repositorio.UserJpaRepo;
import pe.com.app.user.util.UserMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJpaRepo usuarioJpaRepo;
    @Override
    @Transactional
    public Mono<UserResponse> add(UserRequest request) {
        log.info("add usuario, request : {}", request);
        return Mono.just(usuarioJpaRepo.findByEmail(request.getEmail()))
                .flatMap(userEntity -> {
                    if (userEntity.isPresent()) return Mono.error(new BusinessException("El correo ya registrado"));
                    UserEntity user =  usuarioJpaRepo.save(UserMapper.toEntityActive(request));
                    return Mono.just(UserMapper.toUserResponse(user));
                })
                .onErrorResume(throwable -> {
                    log.error("Error en la creacion del usuario :: " + throwable.getMessage());
                    return Mono.error(throwable);
                });

    }

    @Override
    public Mono<UserResponse> put(UserUpdateRequest request) {
        log.info("patch usuario, request : {}", request);
        return Mono.just(usuarioJpaRepo.findById(request.getId()))
                .flatMap(userEntity -> {
                    if (!userEntity.isPresent()) return Mono.error(new BusinessException("Usuario no existe"));
                    UserEntity objupdate = userEntity.get();
                    objupdate.setEmail(request.getEmail());
                    objupdate.setPassword(request.getPassword());
                    objupdate.setModified(LocalDateTime.now());
                    UserEntity user =  usuarioJpaRepo.save(objupdate);
                    return Mono.just(UserMapper.toUserResponse(user));
                })
                .onErrorResume(throwable -> {
                    log.error("Error en la actualizacion del usuario :: " + throwable.getMessage());
                    return Mono.error(throwable);
                });
    }

    @Override
    public Mono<UserResponse> delete(UUID id) {
        log.info("delete usuario, id : {}", id);
        return Mono.just(usuarioJpaRepo.findById(id))
                .flatMap(userEntity -> {
                    if (!userEntity.isPresent()) return Mono.error(new BusinessException("Usuario no existe"));
                    if ("INACTIVE".equals(userEntity.get().getState())) return Mono.error(new BusinessException("Usuario ya esta inactivo"));
                    UserEntity objupdate = userEntity.get();
                    objupdate.setState("INACTIVE");
                    objupdate.setModified(LocalDateTime.now());
                    UserEntity user =  usuarioJpaRepo.save(objupdate);
                    return Mono.just(UserMapper.toUserResponse(user));
                })
                .onErrorResume(throwable -> {
                    log.error("Error en la eliminacion logica del usuario :: " + throwable.getMessage());
                    return Mono.error(throwable);
                });
    }

    @Override
    public Mono<UserDetailResponse> getById(UUID id) {
        log.info("getById usuario, id : {}", id);
        return Mono.just(usuarioJpaRepo.findById(id))
                .flatMap(userEntity -> {
                    if (!userEntity.isPresent()) return Mono.error(new BusinessException("Usuario no existe"));
                    if ("INACTIVE".equals(userEntity.get().getState())) return Mono.error(new BusinessException("Usuario esta Inactivo"));
                    return Mono.just(UserMapper.toUserDetailResponse(userEntity.get()));
                })
                .onErrorResume(throwable -> {
                    log.error("Error en la obtencion del usuario :: " + throwable.getMessage());
                    return Mono.error(throwable);
                });
    }

    @Override
    public Flux<UserResponse> getAll() {
        List<UserEntity> list = usuarioJpaRepo.findAll();
        if (list.isEmpty())  return Flux.error(new IllegalArgumentException("La lista de usuarios esta vacia"));
        else  return Flux.fromIterable(UserMapper.toUserResponseList(list));
    }

    @Override
    public Mono<UserResponse> changeStatus(UserStatusRequest request) {
        log.info("changeStatus changeStatus, request : {}", request);
        return Mono.just(usuarioJpaRepo.findById(request.getId()))
                .flatMap(userEntity -> {
                    if (!userEntity.isPresent()) return Mono.error(new BusinessException("Usuario no existe"));
                    UserEntity objupdate = userEntity.get();
                    objupdate.setState(request.getStatus());
                    objupdate.setModified(LocalDateTime.now());
                    UserEntity user =  usuarioJpaRepo.save(objupdate);
                    return Mono.just(UserMapper.toUserResponse(user));
                })
                .onErrorResume(throwable -> {
                    log.error("Error en el cambio de estado del usuario :: " + throwable.getMessage());
                    return Mono.error(throwable);
                });
    }


}
