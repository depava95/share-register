package ua.biedin.register.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import ua.biedin.register.entity.CompanyShare;
import ua.biedin.register.exception.NoRevisionsAvailableException;
import ua.biedin.register.exception.NoSharesAvailableException;
import ua.biedin.register.exception.SaveOrUpdateShareException;
import ua.biedin.register.repository.CompanyShareRepository;
import ua.biedin.register.service.CompanyShareService;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CompanyShareServiceImpl implements CompanyShareService {

    private final CompanyShareRepository repository;

    @Autowired
    public CompanyShareServiceImpl(CompanyShareRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompanyShare createShare(CompanyShare share) {
        return repository.save(share);
    }

    @Transactional
    @Override
    public CompanyShare update(long id, @NotNull CompanyShare share) {
        CompanyShare companyShare = repository.findById(id).orElseThrow(SaveOrUpdateShareException::new);

        companyShare.setTotalFaceValue(share.getFaceValue().multiply(BigDecimal.valueOf(share.getAmount())));
        companyShare.setAmount(share.getAmount());
        companyShare.setCapitalSize(share.getCapitalSize());
        companyShare.setComment(share.getComment());
        companyShare.setFaceValue(share.getFaceValue());
        companyShare.setStateDutyPaid(share.getStateDutyPaid());
        companyShare.setUsreou(share.getUsreou());

        return repository.save(companyShare);
    }

    @Override
    public @NotNull List<CompanyShare> getAllShares() {
        return repository.findAll();
    }

    @Override
    public @NotNull List<CompanyShare> getAllShares(@NotNull Predicate predicate) {
        List<CompanyShare> companyShares = new ArrayList<>();
        repository.findAll(predicate).forEach(companyShares::add);

        return companyShares;
    }

    @Override
    public @NotNull Page<CompanyShare> getAllShares(@NotNull Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public @NotNull Page<CompanyShare> getAllShares(@NotNull Predicate predicate, @NotNull Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public @NotNull CompanyShare getPublicDataOfShare(long id) {
        return repository.findById(id).orElseThrow(NoSharesAvailableException::new);
    }

    @Override
    public @NotNull List<CompanyShare> getAllPublicSharesByCompany(int usreou) {
        return repository.findAllByUsreou(usreou);
    }

    @Override
    public @NotNull Page<CompanyShare> getAllPublicSharesByCompany(int usreou, @NotNull Pageable pageable) {
        return repository.findAllByUsreou(usreou, pageable);
    }

    @Override
    // TODO: refactor response object
    public Page<Revision<Integer, CompanyShare>> getPrivateDataOfShare(@NonNull long id, Pageable pageable) {
        Page<Revision<Integer, CompanyShare>> revisions = repository.findRevisions(id, pageable);
        log.info("Revisions of share with {} id are successfully loaded", id);
        if (revisions.isEmpty()) {
            throw new NoRevisionsAvailableException();
        }
        return revisions;
    }

    @Transactional
    @Override
    // TODO: refactor response object
    public List<Page<Revision<Integer, CompanyShare>>> getPrivateDataOfShareByCompany(int usreou, Pageable pageable) {
        List<Page<Revision<Integer, CompanyShare>>> list = new ArrayList<>();
        List<Long> id = repository.findAllIdByUsreou(usreou);
        for (Long aLong : id) {
            list.add(repository.findRevisions(aLong, pageable));
        }
        log.info("Revisions of share with {} id company are successfully loaded", usreou);
        return list;
    }
}
