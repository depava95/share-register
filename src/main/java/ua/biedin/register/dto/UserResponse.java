package ua.biedin.register.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.biedin.register.entity.User;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserResponse {

    Long id;
    String login;

    public UserResponse(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
    }
}
