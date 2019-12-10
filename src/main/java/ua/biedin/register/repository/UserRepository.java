package ua.biedin.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.biedin.register.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
