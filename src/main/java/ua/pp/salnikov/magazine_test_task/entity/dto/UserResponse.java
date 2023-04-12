package ua.pp.salnikov.magazine_test_task.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.pp.salnikov.magazine_test_task.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserResponse {

    private Integer id;
    private String name;
    private Integer age;

    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .build();
    }

}
