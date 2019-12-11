package ua.biedin.register.dto;

import lombok.Data;
import ua.biedin.register.entity.User;

@Data
public class UserResponse {
    Long id;
    String login;

    public UserResponse(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
    }
}
