package ua.biedin.register.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.biedin.register.entity.CompanyShare;

public interface CompanyShareService {

    CompanyShare createShare(CompanyShare share);

    Page<CompanyShare> getPublicDataOfShares(Pageable pageable);

    Page<CompanyShare> getPrivateDataOfShare(Pageable pageable);

    Page<CompanyShare> getAllPublicSharesByCompany(int usreou, Pageable pageable);

    Page<CompanyShare> getAllPrivateSharesByCompany(int usreou, Pageable pageable);

    CompanyShare update(Long id, CompanyShare share);

}
