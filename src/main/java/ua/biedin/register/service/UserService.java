package ua.biedin.register.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import ua.biedin.register.entity.User;
import ua.biedin.register.security.jwt.JwtTokenProvider;

public interface UserService {

    User registration(User user);

    User findByLogin(String login);

    ResponseEntity login(User user, AuthenticationManager manager, JwtTokenProvider token);

}
