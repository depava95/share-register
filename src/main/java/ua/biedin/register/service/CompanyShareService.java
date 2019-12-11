package ua.biedin.register.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ua.biedin.register.entity.CompanyShare;
import ua.biedin.register.util.Constants;

public interface CompanyShareService {

    CompanyShare createShare (CompanyShare share);

    CompanyShare editShare (CompanyShare share);

    Page<CompanyShare> getPublicDataOfShares(Pageable pageable);

    Page<CompanyShare> getPrivateDataOfShare();

    Page<CompanyShare> getAllSharesByCompany (Integer USREOU, Pageable pageable);

     default Pageable createPagination(int size, int page, String sort, String direction) {
        if (direction.equals(Constants.ASC)) {
            return PageRequest.of(page, size, Sort.by(sort).ascending());
        } else {
            return PageRequest.of(page, size, Sort.by(sort).descending());
        }
    }
}
