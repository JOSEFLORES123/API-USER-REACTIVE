package pe.com.app.user.servicio;

import pe.com.app.user.control.request.UserRequest;
import pe.com.app.user.control.request.UserStatusRequest;
import pe.com.app.user.control.request.UserUpdateRequest;
import pe.com.app.user.control.response.UserDetailResponse;
import pe.com.app.user.control.response.UserResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {
    Mono<UserResponse> add(UserRequest request);
    Mono<UserResponse> put(UserUpdateRequest request);
    Mono<UserResponse> delete(UUID id);
    Mono<UserDetailResponse> getById(UUID id);

    Flux<UserResponse> getAll();

    Mono<UserResponse> changeStatus(UserStatusRequest request);
}
