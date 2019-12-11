package ua.biedin.register.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.biedin.register.dto.UserLoginDTO;
import ua.biedin.register.dto.UserResponse;
import ua.biedin.register.entity.User;
import ua.biedin.register.security.jwt.JwtTokenProvider;
import ua.biedin.register.service.UserService;

import javax.validation.Valid;

@Slf4j
@RestController
public class UserLoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public UserLoginController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping(value = "/api/v1/login", consumes = "application/json")
    public ResponseEntity login(@RequestBody UserLoginDTO userLoginDTO) {
        User user = User.builder()
                .login(userLoginDTO.getLogin())
                .password(userLoginDTO.getPassword())
                .build();
        return userService.login(user, authenticationManager, jwtTokenProvider);
    }

    @PostMapping(value = "/api/v1/registration", consumes = "application/json")
    public ResponseEntity<UserResponse> registration(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        User candidate = User
                .builder()
                .login(userLoginDTO.getLogin())
                .password(userLoginDTO.getPassword())
                .build();
        User registration = userService.registration(candidate);
        UserResponse userResponse = new UserResponse(registration);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}