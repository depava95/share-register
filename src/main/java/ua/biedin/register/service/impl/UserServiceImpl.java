package ua.biedin.register.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.biedin.register.entity.Roles;
import ua.biedin.register.entity.User;
import ua.biedin.register.repository.RoleRepository;
import ua.biedin.register.repository.UserRepository;
import ua.biedin.register.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    @Override
    public User register(User user) {
        Roles role = roleRepository.findByName("USER");
        List<Roles> rolesList = new ArrayList<>();
        rolesList.add(role);
        //TODO Проверить работу кодировки пароля
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(rolesList);
        userRepository.save(user);
        //TODO Добавить всякие проверки и эксепшены
        log.info("User {} successfully registered", user.getLogin());

        return user;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        //TODO Добавить всякие проверки и эксепшены
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        //TODO Добавить всякие проверки и эксепшены
        return userRepository.findByLogin(login);
    }

}
