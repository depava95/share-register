package ua.biedin.register.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.biedin.register.entity.CompanyShare;

@Repository
public interface CompanyShareRepository extends JpaRepository<CompanyShare, Long> {

    Page<CompanyShare> findAllByUSREOU(Integer USREOU, Pageable pageable);

    Page<CompanyShare> findAll(Pageable pageable);

}
