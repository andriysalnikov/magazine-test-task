package ua.pp.salnikov.magazine_test_task.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.pp.salnikov.magazine_test_task.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserLoginResponse {

    private String token;
    @JsonProperty("user")
    private UserResponse userResponse;

    public static UserLoginResponse fromUser(String token, User user) {
        return UserLoginResponse.builder()
                .token(token)
                .userResponse(UserResponse.fromUser(user))
                .build();
    }

}
