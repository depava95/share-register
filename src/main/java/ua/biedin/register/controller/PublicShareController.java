package ua.biedin.register.controller;

import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.biedin.register.controller.response.PublicShareResponse;
import ua.biedin.register.entity.CompanyShare;
import ua.biedin.register.entity.QCompanyShare;
import ua.biedin.register.mappers.CompanyShareMapper;
import ua.biedin.register.service.CompanyShareService;
import ua.biedin.register.util.Constants;
import ua.biedin.register.util.PaginationHelper;


@Slf4j
@RestController
@RequestMapping(value = Constants.PATH_PUBLIC, produces = "application/json", consumes = "application/json")
public class PublicShareController {

    private final CompanyShareService companyShareService;

    @Autowired
    public PublicShareController(CompanyShareService companyShareService) {
        this.companyShareService = companyShareService;
    }

    @GetMapping(value = "share")
    public ResponseEntity<Page<PublicShareResponse>> getShares(
            @RequestParam(required = false, defaultValue = "0", name = "page") int page,
            @RequestParam(required = false, defaultValue = "10", name = "size") int size,
            @RequestParam(required = false, defaultValue = "releaseDate", name = "sort") String sort,
            @RequestParam(required = false, defaultValue = "asc", name = "direction") String direction,
            @QuerydslPredicate(root = CompanyShare.class) Predicate predicate) {
        Pageable pageable = PaginationHelper.createPagination(size, page, sort, direction);
        if (predicate == null) {
            predicate = QCompanyShare.companyShare.comment.ne("");
        }
        Page<CompanyShare> shares = companyShareService.getPublicDataOfShares(predicate, pageable);
        Page<PublicShareResponse> publicShares = shares.map(CompanyShareMapper.INSTANCE::toPublicResponse);
        log.info("{} shares are showed", publicShares.getSize());

        return new ResponseEntity<>(publicShares, HttpStatus.OK);
    }

    @GetMapping(value = "share/company/{usreou}")
    public ResponseEntity<Page<PublicShareResponse>> getShares(
            @PathVariable(name = "usreou") int usreou,  // ЕДРПОУ
            @RequestParam(required = false, defaultValue = "0", name = "page") int page,
            @RequestParam(required = false, defaultValue = "10", name = "size") int size,
            @RequestParam(required = false, defaultValue = "releaseDate", name = "sort") String sort,
            @RequestParam(required = false, defaultValue = "asc", name = "direction") String direction) {
        Pageable pageable = PaginationHelper.createPagination(size, page, sort, direction);
        Page<CompanyShare> shares = companyShareService.getAllPublicSharesByCompany(usreou, pageable);
        Page<PublicShareResponse> publicShares = shares.map(CompanyShareMapper.INSTANCE::toPublicResponse);
        log.info("{} shares by company with {} id are showed", publicShares.getSize(), usreou);

        return new ResponseEntity<>(publicShares, HttpStatus.OK);
    }

    @GetMapping(value = "share/{id}")
    public ResponseEntity<PublicShareResponse> getShare(@PathVariable(name = "id") Long id) {
        CompanyShare share = companyShareService.getPublicDataOfShare(id);
        PublicShareResponse shareResponse = CompanyShareMapper.INSTANCE.toPublicResponse(share);
        return new ResponseEntity<>(shareResponse, HttpStatus.OK);
    }
}
