package pe.com.app.user.control;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.com.app.user.control.request.UserRequest;
import pe.com.app.user.control.request.UserStatusRequest;
import pe.com.app.user.control.request.UserUpdateRequest;
import pe.com.app.user.control.response.UserDetailResponse;
import pe.com.app.user.control.response.UserResponse;
import pe.com.app.user.servicio.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserControl {

    private final UserService userService;

    @Operation(summary = "Operation to add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operation success"),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserResponse> add(@Valid @RequestBody final UserRequest request) {
        return userService.add(request);
    }




    @Operation(summary = "Operation to update information of user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operation success"),
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserResponse> put(@RequestBody final UserUpdateRequest request) {
        return userService.put(request);
    }




    @Operation(summary = "Operation to change status user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operation success"),
    })
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserResponse> patch(@RequestBody final UserStatusRequest request) {
        return userService.changeStatus(request);
    }




    @Operation(summary = "Operation to delete an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operation success"),
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserResponse> delete(@PathVariable("id") final UUID id) {
        return userService.delete(id);
    }




    @Operation(summary = "Operation to get an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operation success"),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserDetailResponse> getById(@PathVariable("id") final UUID id) {
        return userService.getById(id);
    }




    @Operation(summary = "Operation to get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operation success"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<UserResponse> getAll() {
        return userService.getAll();
    }

}
