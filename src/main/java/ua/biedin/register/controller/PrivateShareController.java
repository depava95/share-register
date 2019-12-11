package ua.biedin.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.biedin.register.service.CompanyShareService;

@RestController
@RequestMapping("api/v1/private/")
public class PrivateShareController {

    private final CompanyShareService companyShareService;

    @Autowired
    public PrivateShareController(CompanyShareService companyShareService) {
        this.companyShareService = companyShareService;
    }


}
