package ua.biedin.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.biedin.register.dto.PublicShareResponse;
import ua.biedin.register.entity.CompanyShare;
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

    @GetMapping(value = "shares", produces = "application/json")
    public ResponseEntity<Page<PublicShareResponse>> getShares(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "releaseDate") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        Pageable pageable = companyShareService.createPagination(size, page, sort, direction);
        Page<CompanyShare> shares = companyShareService.getPublicDataOfShares(pageable);
        Page<PublicShareResponse> publicShares = shares.map(CompanyShareMapper.INSTANCE::toPublicResponse);
        return new ResponseEntity<>(publicShares, HttpStatus.OK);
    }

}
