package ua.biedin.register.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import ua.biedin.register.entity.CompanyShare;

import java.util.List;

@Repository
public interface CompanyShareRepository extends RevisionRepository<CompanyShare, Long, Integer>, JpaRepository<CompanyShare, Long> {

    Page<CompanyShare> findAllByUsreou(int usreou, Pageable pageable);

    @Query(value = "SELECT c.id FROM company_share as c WHERE c.usreou = ?", nativeQuery = true)
    List<Long> findAllIdByUsreou(int usreou);
}
