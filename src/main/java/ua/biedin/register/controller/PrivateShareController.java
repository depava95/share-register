package ua.biedin.register.controller;

import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.biedin.register.controller.request.PrivateShareRequest;
import ua.biedin.register.controller.response.PrivateShareResponse;
import ua.biedin.register.entity.CompanyShare;
import ua.biedin.register.entity.QCompanyShare;
import ua.biedin.register.exception.SaveOrUpdateShareException;
import ua.biedin.register.mappers.CompanyShareMapper;
import ua.biedin.register.service.CompanyShareService;
import ua.biedin.register.util.Constants;
import ua.biedin.register.util.PaginationHelper;

import java.math.BigDecimal;
import java.util.List;

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
    public ResponseEntity<PrivateShareResponse> createShare(@RequestBody PrivateShareRequest privateShareRequest) {
        CompanyShare share = CompanyShareMapper.INSTANCE.toShareFromPrivateRequest(privateShareRequest);
        share.setTotalFaceValue(share.getFaceValue().multiply(BigDecimal.valueOf(share.getAmount())));
        CompanyShare shareFromDb = companyShareService.createShare(share);
        if (shareFromDb == null) {
            throw new SaveOrUpdateShareException();
        }
        log.info("Share with {} id is created", shareFromDb.getId());
        return new ResponseEntity<>(CompanyShareMapper.INSTANCE.toPrivateResponse(shareFromDb), HttpStatus.CREATED);
    }

    @PutMapping(value = "share/{id}")
    public ResponseEntity<PrivateShareResponse> updateShare(@PathVariable(name = "id") Long id, @RequestBody PrivateShareRequest privateShareRequest) {
        CompanyShare share = CompanyShareMapper.INSTANCE.toShareFromPrivateRequest(privateShareRequest);
        CompanyShare update = companyShareService.update(id, share);
        log.info("Share with {} id is updated", update.getId());
        return new ResponseEntity<>(CompanyShareMapper.INSTANCE.toPrivateResponse(update), HttpStatus.OK);
    }

    @GetMapping(value = "share/{id}")
    public ResponseEntity<Page<Revision<Integer, CompanyShare>>> getShareWithHistory(
            @PathVariable(name = "id") Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "releaseDate") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        Pageable pageable = PaginationHelper.createPagination(size, page, sort, direction);
        log.info("Trying to loud revisions of share");

        return new ResponseEntity<>(companyShareService.getPrivateDataOfShare(id, pageable), HttpStatus.OK);
    }


    //TODO Сделать, чтоб возвращал Page (Custom RevisionEntity)
    @GetMapping(value = "share/company/{usreou}")
    public ResponseEntity<List<Page<Revision<Integer, CompanyShare>>>> getShareWithHistoryByCompany(
            @PathVariable(name = "usreou") int usreou,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "releaseDate") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        Pageable pageable = PaginationHelper.createPagination(size, page, sort, direction);
        log.info("Revisions of share with {} id company are successfully  louded", usreou);
        return new ResponseEntity<>(companyShareService.getPrivateDataOfShareByCompany(usreou, pageable), HttpStatus.OK);
    }


    @GetMapping(value = "share")
    public ResponseEntity<Page<PrivateShareResponse>> getAllShares(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "releaseDate") String sort,
            @RequestParam(defaultValue = "asc") String direction,
            @QuerydslPredicate(root = CompanyShare.class) Predicate predicate) {
        Pageable pageable = PaginationHelper.createPagination(size, page, sort, direction);
        if (predicate == null) {
            predicate = QCompanyShare.companyShare.comment.ne("");
        }
        Page<CompanyShare> shares = companyShareService.getPrivateDataOfShares(predicate, pageable);
        Page<PrivateShareResponse> privateShares = shares.map(CompanyShareMapper.INSTANCE::toPrivateResponse);
        log.info("{} shares are showed", privateShares.getSize());

        return new ResponseEntity<>(privateShares, HttpStatus.OK);
    }
}
