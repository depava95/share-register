package ua.biedin.register.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import ua.biedin.register.entity.CompanyShare;

@Repository
public interface CompanyShareRepository extends RevisionRepository<CompanyShare, Long, Integer>, JpaRepository<CompanyShare, Long> {

    Page<CompanyShare> findAllByUsreou(int usreou, Pageable pageable);
}
