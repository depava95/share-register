package ua.biedin.register.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import ua.biedin.register.entity.CompanyShare;

public interface CompanyShareService {

    CompanyShare createShare(CompanyShare share);

    Page<CompanyShare> getPublicDataOfShares(Pageable pageable);

    Page<CompanyShare> getPrivateDataOfShares(Pageable pageable);

    Page<Revision<Integer, CompanyShare>> getPrivateDataOfShare(Long id, Pageable pageable);

    CompanyShare getPublicDataOfShare(Long id);

    Page<CompanyShare> getAllPublicSharesByCompany(int usreou, Pageable pageable);

    Page<CompanyShare> getAllPrivateSharesByCompany(int usreou, Pageable pageable);

    CompanyShare update(Long id, CompanyShare share);

}
