package ua.biedin.register.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.biedin.register.dto.UserLoginDTO;
import ua.biedin.register.dto.UserResponse;
import ua.biedin.register.entity.User;
import ua.biedin.register.security.jwt.JwtTokenProvider;
import ua.biedin.register.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UserLoginController {

    private final AuthenticationManager authenticate;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public UserLoginController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticate = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }


    @PostMapping(value = "/api/v1/login", consumes = "application/json")
    public ResponseEntity login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            String username = userLoginDTO.getLogin();
            authenticate.authenticate(new UsernamePasswordAuthenticationToken(username, userLoginDTO.getPassword()));
            User account = userService.findByLogin(username);

            if (account == null) {
                throw new UsernameNotFoundException(
                        "User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, account.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("Login", username);
            response.put("Your token", "Bearer_"+token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping(value = "/api/v1/registration", consumes = "application/json")
    public ResponseEntity<UserResponse> registration (@RequestBody UserLoginDTO userLoginDTO) {
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
