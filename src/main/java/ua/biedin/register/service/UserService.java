package ua.biedin.register.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.biedin.register.entity.User;

public interface UserService {

    User registration(User user);

    void deleteAll();

    Page<User> findAll(Pageable pageable);

    User findByLogin(String login);
}
