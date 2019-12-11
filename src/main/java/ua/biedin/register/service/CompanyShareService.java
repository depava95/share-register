package ua.biedin.register.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.biedin.register.entity.CompanyShare;

public interface CompanyShareService {

    CompanyShare createShare(CompanyShare share);

    Page<CompanyShare> getPublicDataOfShares(Pageable pageable);

    Page<CompanyShare> getPrivateDataOfShare();

    Page<CompanyShare> getAllSharesByCompany(int usreou, Pageable pageable);

    CompanyShare editShare(CompanyShare share);

}
