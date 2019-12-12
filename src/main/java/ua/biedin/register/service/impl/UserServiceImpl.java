package ua.biedin.register.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.biedin.register.controller.request.UserTokenResponse;
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
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
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
        log.debug("User {} successfully registered", user.getLogin());
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

    @Override
    public User initJson(User user) {
        User candidate = User
                .builder()
                .login(user.getLogin())
                .password(encoder.encode(user.getPassword()))
                .roles(user.getRoles())
                .build();
        log.info("in initJson. User created successfully. Info {}", candidate);
        return userRepository.save(candidate);
    }

    public UserTokenResponse login(User request) {
        try {
            String login = request.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, request.getPassword()));
            User account = userRepository.findUserByLogin(login);
            if (account == null) {
                throw new UserLoginNotFoundException();
            }
            String token = jwtTokenProvider.createToken(login, account.getRoles());
            return UserTokenResponse
                    .builder()
                    .login(login)
                    .token("Bearer_".concat(token))
                    .build();
        } catch (
                AuthenticationException e) {
            throw new InvalidUsernameOrPasswordException();
        }
    }
}
