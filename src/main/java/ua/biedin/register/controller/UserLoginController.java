package ua.biedin.register.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.biedin.register.controller.request.UserLoginRequest;
import ua.biedin.register.controller.request.UserTokenResponse;
import ua.biedin.register.controller.response.UserResponse;
import ua.biedin.register.entity.User;
import ua.biedin.register.mappers.UserMapper;
import ua.biedin.register.service.UserService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1", consumes = "application/json")
public class UserLoginController {


    private final UserService userService;

    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<UserTokenResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        User user = UserMapper.INSTANCE.toUserFromLogin(userLoginRequest);

        return new ResponseEntity<>(userService.login(user), HttpStatus.ACCEPTED);
    }

    @PostMapping("registration")
    public ResponseEntity<UserResponse> registration(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        User user = UserMapper.INSTANCE.toUserFromLogin(userLoginRequest);
        User registration = userService.registration(user);
        UserResponse userResponse = UserMapper.INSTANCE.toUserResponse(registration);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}