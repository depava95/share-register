package ua.biedin.register.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.biedin.register.entity.CompanyShare;
import ua.biedin.register.exception.NoSharesAvailableException;
import ua.biedin.register.repository.CompanyShareRepository;
import ua.biedin.register.service.CompanyShareService;

@Slf4j
@Service
public class CompanyShareServiceImpl implements CompanyShareService {

    private final CompanyShareRepository repository;

    @Autowired
    public CompanyShareServiceImpl(CompanyShareRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompanyShare createShare(CompanyShare share) {
        return repository.save(share);
    }

    @Override
    public CompanyShare editShare(CompanyShare share) {
        return null;
    }

    @Override
    public Page<CompanyShare> getPublicDataOfShares(Pageable pageable) {
        Page<CompanyShare> shares = repository.findAll(pageable);
        if (shares.isEmpty()) {
            throw new NoSharesAvailableException();
        }
        return shares;
        //TODO LOGi
    }

    @Override
    public Page<CompanyShare> getPrivateDataOfShare() {
        return null;
    }

    @Override
    public Page<CompanyShare> getAllSharesByCompany(int usreou, Pageable pageable) {
        Page<CompanyShare> shares = repository.findAllByUSREOU(usreou, pageable);
        if (shares.isEmpty()) {
            throw new NoSharesAvailableException();
        }
        return shares;
    }
}
