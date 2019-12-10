package ua.biedin.register.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.biedin.register.service.impl.CompanyShareServiceImpl;

@RestController
@Slf4j
@RequestMapping(path = "share/api/v1/public")
public class CompanyShareController {

    private final CompanyShareServiceImpl service;

    @Autowired
    public CompanyShareController(CompanyShareServiceImpl service) {
        this.service = service;
    }

}
