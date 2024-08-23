package pe.com.app.user.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pe.com.app.user.control.request.PhoneRequest;
import pe.com.app.user.control.request.UserRequest;
import pe.com.app.user.control.response.UserDetailResponse;
import pe.com.app.user.control.response.UserResponse;
import pe.com.app.user.modelo.PhoneEntity;
import pe.com.app.user.modelo.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static UserEntity toEntityActive(UserRequest request){
        if(request == null) return null;
        UserEntity userEntity = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .state("ACTIVE")
                .lastLogin(LocalDateTime.now())
                .build();

        List <PhoneEntity> list = request.getPhones().stream().map(
                phoneRequest -> PhoneEntity.builder()
                        .number(phoneRequest.getNumber())
                        .contrycode(phoneRequest.getContrycode())
                        .citycode(phoneRequest.getCitycode())
                        .build()
        ).collect(Collectors.toList());

        for (PhoneEntity phone : list) {
            phone.setUser(userEntity);
        }
        userEntity.setPhones(list);

        return userEntity;
    }

    public static UserResponse toUserResponse(UserEntity obj){
        if(obj == null) return null;
        return UserResponse.builder()
                .id(obj.getId())
                .email(obj.getEmail())
                .created(obj.getCreated().toString())
                .modified(obj.getModified().toString())
                .token("No hay token aun")
                .isactive(obj.getState())
                .last_login(obj.getLastLogin().toString())
                .build();
    }

    public static List<UserResponse> toUserResponseList(List<UserEntity> lista) {
        return lista.stream().map(entity -> toUserResponse(entity)).collect(Collectors.toList());
    }

    public static UserDetailResponse toUserDetailResponse(UserEntity obj) {
        if(obj == null) return null;
        return UserDetailResponse.builder()
                .id(obj.getId())
                .email(obj.getEmail())
                .created(obj.getCreated().toString())
                .modified(obj.getModified().toString())
                .token("No hay token aun")
                .isactive(obj.getState())
                .last_login(obj.getLastLogin().toString())
                .phones(
                        obj.getPhones().stream().map(
                         phoneEntity ->  PhoneRequest.builder()
                                 .id(phoneEntity.getId())
                                 .citycode(phoneEntity.getCitycode())
                                 .contrycode(phoneEntity.getContrycode())
                                 .number(phoneEntity.getNumber())
                                 .build()
                        ).collect(Collectors.toList())
                )
                .build();
    }
}
