package ua.biedin.register.service;

import ua.biedin.register.controller.request.UserTokenResponse;
import ua.biedin.register.entity.User;

public interface UserService {

    User registration(User user);

    User findByLogin(String login);

    UserTokenResponse login(User user);

}
