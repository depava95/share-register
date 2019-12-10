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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.biedin.register.dto.UserLoginDTO;
import ua.biedin.register.entity.User;
import ua.biedin.register.security.jwt.JwtTokenProvider;
import ua.biedin.register.service.UserService;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
public class LoginController {

    private final AuthenticationManager authenticate;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticate = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }



    @PostMapping("/api/v1/login")
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
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
