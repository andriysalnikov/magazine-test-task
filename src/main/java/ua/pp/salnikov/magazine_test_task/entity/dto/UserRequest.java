package ua.pp.salnikov.magazine_test_task.entity.dto;


import lombok.*;
import ua.pp.salnikov.magazine_test_task.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserRequest {

    @NotNull(message = "Missing 'name' field")
    @NotBlank(message = "User 'name' can not be empty")
    private String name;

    @NotNull(message = "Missing 'password' field")
    @NotBlank(message = "User 'password' can not be empty")
    private String password;

    @NotNull(message = "Missing correct 'age' field")
    private Integer age;

    public static User toUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .age(userRequest.getAge())
                .build();
    }

}
