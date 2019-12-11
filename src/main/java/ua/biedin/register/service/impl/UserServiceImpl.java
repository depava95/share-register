package ua.biedin.register.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.biedin.register.entity.Roles;
import ua.biedin.register.entity.User;
import ua.biedin.register.exception.InvalidUsernameOrPasswordException;
import ua.biedin.register.exception.UserLoginNotFoundException;
import ua.biedin.register.repository.RoleRepository;
import ua.biedin.register.repository.UserRepository;
import ua.biedin.register.security.jwt.JwtTokenProvider;
import ua.biedin.register.service.UserService;
import ua.biedin.register.util.Constants;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }


    @Transactional
    @Override
    public User registration(User user) {
        Roles role = roleRepository.findFirstByName(Constants.ROLE_USER);
        List<Roles> rolesList = new ArrayList<>();
        rolesList.add(role);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(rolesList);
        User userFromDb = userRepository.save(user);
        log.info("User {} successfully registered", user.getLogin());
        return userFromDb;
    }

    @Override
    public User findByLogin(String login) {
        User userByLogin = userRepository.findUserByLogin(login);
        if (userByLogin == null) {
            throw new UserLoginNotFoundException();
        }
        return userByLogin;
    }

    public ResponseEntity login(User request, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        try {
            String login = request.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, request.getPassword()));
            User account = userRepository.findUserByLogin(login);

            if (account == null) {
                throw new UsernameNotFoundException(
                        "User with username: " + login + " not found");
            }
            String token = jwtTokenProvider.createToken(login, account.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("Login", login);
            response.put("Your token", "Bearer_" + token);
            return ResponseEntity.ok(response);
        } catch (
                AuthenticationException e) {
            throw new InvalidUsernameOrPasswordException();
        }
    }
}
