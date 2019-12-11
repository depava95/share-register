package ua.biedin.register.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.biedin.register.controller.response.PublicShareResponse;
import ua.biedin.register.entity.CompanyShare;
import ua.biedin.register.mappers.CompanyShareMapper;
import ua.biedin.register.service.CompanyShareService;
import ua.biedin.register.util.PaginationHelper;

@Slf4j
@RestController
@RequestMapping(value = "api/v1/public", produces = "application/json", consumes = "application/json")
public class PublicShareController {

    private final CompanyShareService companyShareService;

    @Autowired
    public PublicShareController(CompanyShareService companyShareService) {
        this.companyShareService = companyShareService;
    }

    @GetMapping(value = "share")
    public ResponseEntity<Page<PublicShareResponse>> getShares(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "releaseDate") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        Pageable pageable = PaginationHelper.createPagination(size, page, sort, direction);
        Page<CompanyShare> shares = companyShareService.getPublicDataOfShares(pageable);
        Page<PublicShareResponse> publicShares = shares.map(CompanyShareMapper.INSTANCE::toPublicResponse);

        return new ResponseEntity<>(publicShares, HttpStatus.OK);
    }

    @GetMapping(value = "share/company/{usreou}")
    public ResponseEntity<Page<PublicShareResponse>> getShares(
            @PathVariable(name = "usreou") int usreou,  // ЕДРПОУ
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "releaseDate") String sort,
            @RequestParam(required = false, defaultValue = "asc") String direction) {
        Pageable pageable = PaginationHelper.createPagination(size, page, sort, direction);
        Page<CompanyShare> shares = companyShareService.getAllPublicSharesByCompany(usreou, pageable);
        Page<PublicShareResponse> publicShares = shares.map(CompanyShareMapper.INSTANCE::toPublicResponse);

        return new ResponseEntity<>(publicShares, HttpStatus.OK);
    }
}
