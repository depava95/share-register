package ua.biedin.register.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.biedin.register.controller.request.PrivateShareRequest;
import ua.biedin.register.controller.response.PrivateShareResponse;
import ua.biedin.register.entity.CompanyShare;
import ua.biedin.register.exception.SaveOrUpdateShareException;
import ua.biedin.register.mappers.CompanyShareMapper;
import ua.biedin.register.service.CompanyShareService;
import ua.biedin.register.util.Constants;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping(value = Constants.PATH_PRIVATE, produces = "application/json", consumes = "application/json")
public class PrivateShareController {

    private final CompanyShareService companyShareService;

    @Autowired
    public PrivateShareController(CompanyShareService companyShareService) {
        this.companyShareService = companyShareService;
    }

    @PostMapping(value = "share")
    public ResponseEntity<PrivateShareResponse> createShare (@RequestBody PrivateShareRequest privateShareRequest) {
        CompanyShare share = CompanyShareMapper.INSTANCE.toShareFromPrivateRequest(privateShareRequest);
        share.setTotalFaceValue(share.getFaceValue().multiply(BigDecimal.valueOf(share.getAmount())));
        CompanyShare shareFromDb = companyShareService.createShare(share);
        if (shareFromDb == null) {
            throw new SaveOrUpdateShareException();
        }
        return new ResponseEntity<>(CompanyShareMapper.INSTANCE.toPrivateResponse(shareFromDb), HttpStatus.CREATED);
    }

    @PutMapping(value = "share/{id}")
    public ResponseEntity<PrivateShareResponse> updateShare (@PathVariable(name = "id") Long id, @RequestBody PrivateShareRequest privateShareRequest) {
        CompanyShare share = CompanyShareMapper.INSTANCE.toShareFromPrivateRequest(privateShareRequest);
        CompanyShare update = companyShareService.update(id, share);

        return new ResponseEntity<>(CompanyShareMapper.INSTANCE.toPrivateResponse(update), HttpStatus.OK);
    }
}
