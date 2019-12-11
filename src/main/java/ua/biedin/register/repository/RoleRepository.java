package ua.biedin.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.biedin.register.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

    Roles findFirstByName(String name);

}
