package ua.biedin.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.biedin.register.dto.PublicShareResponse;
import ua.biedin.register.entity.CompanyShare;
import ua.biedin.register.service.CompanyShareService;
import ua.biedin.register.service.impl.UserServiceImpl;

@RestController
@RequestMapping("api/v1/public/")
public class PublicShareController {

    private final CompanyShareService companyShareService;

    @Autowired
    public PublicShareController(CompanyShareService companyShareService) {
        this.companyShareService = companyShareService;
    }

    @GetMapping("shares")
    public ResponseEntity<PublicShareResponse> getShares(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "create") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        Pageable pageable = companyShareService.createPagination(size, page, sort, direction);
        Page<CompanyShare> publicDataOfShares = companyShareService.getPublicDataOfShares(pageable);

        return null;
    }

}
