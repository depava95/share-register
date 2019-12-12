package ua.biedin.register.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import ua.biedin.register.entity.CompanyShare;

import java.util.List;

public interface CompanyShareService {

    CompanyShare createShare(CompanyShare share);

    Page<CompanyShare> getPublicDataOfShares(Pageable pageable);

    Page<CompanyShare> getPrivateDataOfShares(Pageable pageable);

    Page<Revision<Integer, CompanyShare>> getPrivateDataOfShare(Long id, Pageable pageable);

    List<Page<Revision<Integer, CompanyShare>>> getPrivateDataOfShareByCompany(int usreou, Pageable pageable);

    CompanyShare getPublicDataOfShare(Long id);

    Page<CompanyShare> getAllPublicSharesByCompany(int usreou, Pageable pageable);

    CompanyShare update(Long id, CompanyShare share);


}
