package pe.com.app.user;

import pe.com.app.user.control.request.PhoneRequest;
import pe.com.app.user.control.request.UserRequest;
import pe.com.app.user.control.response.UserResponse;
import pe.com.app.user.modelo.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RptUtil {

    public static UserRequest generateUserRequest(){
        UserRequest request = UserRequest.builder()
                .name("Lucy")
                .email("ccc@company.cl")
                .password("1a2b3c4d")
                .phones(
                        Stream.of(
                                PhoneRequest.builder()
                                    .contrycode("3434")
                                    .citycode("565")
                                    .number("78975464564")
                                    .build()
                                )
                                .collect(Collectors.toList())
                )
                .build();
        return request;
    }

    public static UserEntity generateUserEntity(){
        UserEntity obj = new UserEntity();
        obj.setName("test");
        obj.setEmail("hola@company.cl");
        obj.setCreated(LocalDateTime.now());
        obj.setModified(LocalDateTime.now());
        obj.setLastLogin(LocalDateTime.now());
        obj.setState("ACTIVE");
        obj.setId(UUID.randomUUID());
        return obj;
    }

    public static UserResponse generateUserResponse(){
        return UserResponse.builder()
                .id(UUID.randomUUID())
                .email("hola@company.cl")
                .created(LocalDateTime.now().toString())
                .modified(LocalDateTime.now().toString())
                .last_login(LocalDateTime.now().toString())
                .isactive("ACTIVE")
                .build();
    }

    public static List<UserResponse> generateUserResponseList(){
        return Stream.of(generateUserResponse()).collect(Collectors.toList());
    }


    public static UserRequest generateUserRequestWithInvalidFields(){
        UserRequest request = UserRequest.builder()
                .name("")
                .email("ccc@compa@@@@@@ny.cl")
                .password("12345678")
                .phones(
                        Stream.of(
                                        PhoneRequest.builder()
                                                .contrycode("3434")
                                                .citycode("565")
                                                .number("78975464564")
                                                .build()
                                )
                                .collect(Collectors.toList())
                )
                .build();
        return request;
    }
}
