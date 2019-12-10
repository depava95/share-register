package ua.biedin.register.service;

import org.springframework.data.domain.Page;
import ua.biedin.register.entity.CompanyShare;

public interface CompanyShareService {

    CompanyShare createShare (CompanyShare share);

    CompanyShare editShare (CompanyShare share);

    CompanyShare getPublicDataOfShare();

    Page<CompanyShare> getPrivateDataOfShare();

    Page<CompanyShare> getAllSharesByCompany (Integer USREOU);

}
