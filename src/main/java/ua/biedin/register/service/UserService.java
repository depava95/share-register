package ua.biedin.register.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.biedin.register.entity.User;

import java.util.Optional;

public interface UserService {

    User register(User user);

    void deleteAll();

    User initJson(User user);

    Page<User> findAll(Pageable pageable);

    User findByLogin(String login);
}
