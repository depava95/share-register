package ua.biedin.register.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import ua.biedin.register.entity.CompanyShare;

import com.querydsl.core.types.Predicate;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface CompanyShareService {

    CompanyShare createShare(@NotNull CompanyShare share);

    @Transactional
    CompanyShare update(long id, @NotNull CompanyShare share);

    @NotNull List<CompanyShare> getAllShares();

    @NotNull List<CompanyShare> getAllShares(@NotNull Predicate predicate);

    @NotNull Page<CompanyShare> getAllShares(@NotNull Pageable pageable);

    @NotNull Page<CompanyShare> getAllShares(@NotNull Predicate predicate, @NotNull Pageable pageable);

    @NotNull Page<Revision<Integer, CompanyShare>> getPrivateDataOfShare(long id, @NotNull Pageable pageable);

    @NotNull List<Page<Revision<Integer, CompanyShare>>> getPrivateDataOfShareByCompany(int usreou, Pageable pageable);

    @NotNull CompanyShare getPublicDataOfShare(long id);

    @NotNull List<CompanyShare> getAllPublicSharesByCompany(int usreou);

    Page<CompanyShare> getAllPublicSharesByCompany(int usreou, Pageable pageable);

    CompanyShare update(Long id, CompanyShare share);


}
