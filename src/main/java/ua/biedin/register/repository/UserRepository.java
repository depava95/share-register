package ua.biedin.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.biedin.register.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
