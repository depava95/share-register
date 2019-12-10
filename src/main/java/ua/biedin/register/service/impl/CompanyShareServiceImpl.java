package ua.biedin.register.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.biedin.register.repository.CompanyShareRepository;
import ua.biedin.register.service.CompanyShareService;

@Service
public class CompanyShareServiceImpl implements CompanyShareService {

    private final CompanyShareRepository repository;

    @Autowired
    public CompanyShareServiceImpl(CompanyShareRepository repository) {
        this.repository = repository;
    }

}
