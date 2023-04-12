package ua.pp.salnikov.magazine_test_task.entity.dto;

import lombok.*;
import ua.pp.salnikov.magazine_test_task.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserLoginRequest {

    @NotNull(message = "Missing 'name' field")
    @NotBlank(message = "User 'name' can not be empty")
    private String name;

    @NotNull(message = "Missing 'password' field")
    @NotBlank(message = "User 'password' can not be empty")
    private String password;

    public static User toUser(UserRegisterRequest userRegisterRequest) {
        return User.builder()
                .name(userRegisterRequest.getName())
                .password(userRegisterRequest.getPassword())
                .build();
    }

}
