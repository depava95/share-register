package ua.biedin.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.biedin.register.controller.response.PrivateShareResponse;
import ua.biedin.register.entity.CompanyShare;
import ua.biedin.register.exception.SaveOrUpdateShareException;
import ua.biedin.register.mappers.CompanyShareMapper;
import ua.biedin.register.service.CompanyShareService;

@RestController
@RequestMapping(value = "api/v1/private/", produces = "application/json", consumes = "application/json")
public class PrivateShareController {

    private final CompanyShareService companyShareService;

    @Autowired
    public PrivateShareController(CompanyShareService companyShareService) {
        this.companyShareService = companyShareService;
    }

    @PostMapping(value = "share")
    public ResponseEntity<PrivateShareResponse> getShares(PrivateShareResponse privateShareResponse) {
        CompanyShare share = CompanyShareMapper.INSTANCE.toShareFromPrivate(privateShareResponse);
        CompanyShare shareFromDb = companyShareService.createShare(share);
        if (shareFromDb == null) {
            throw new SaveOrUpdateShareException();
        }
        return new ResponseEntity<>(CompanyShareMapper.INSTANCE.toPrivateResponse(shareFromDb), HttpStatus.CREATED);
    }
}
