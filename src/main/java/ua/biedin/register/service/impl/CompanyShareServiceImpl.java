package ua.biedin.register.service.impl;

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
    public CompanyShare update(@NotNull Long id, @NotNull CompanyShare share) {
        share.setTotalFaceValue(share.getFaceValue().multiply(BigDecimal.valueOf(share.getAmount())));
        Optional<CompanyShare> companyShare = repository.findById(id);
        if (companyShare.isEmpty()) {
            throw new SaveOrUpdateShareException();
        }
        companyShare.get().setTotalFaceValue(share.getTotalFaceValue());
        companyShare.get().setAmount(share.getAmount());
        companyShare.get().setCapitalSize(share.getCapitalSize());
        companyShare.get().setComment(share.getComment());
        companyShare.get().setFaceValue(share.getFaceValue());
        companyShare.get().setStateDutyPaid(share.getStateDutyPaid());
        companyShare.get().setUsreou(share.getUsreou());
        return repository.save(companyShare.get());
    }

    @Override
    public Page<CompanyShare> getPublicDataOfShares(Pageable pageable) {
        Page<CompanyShare> shares = repository.findAll(pageable);
        if (shares.isEmpty()) {
            throw new NoSharesAvailableException();
        }
        return shares;
    }

    @Override
    public Page<CompanyShare> getPrivateDataOfShares(Pageable pageable) {
        Page<CompanyShare> shares = repository.findAll(pageable);
        if (shares.isEmpty()) {
            throw new NoSharesAvailableException();
        }
        return shares;
    }

    @Override
    public CompanyShare getPublicDataOfShare(Long id) {
        Optional<CompanyShare> share = repository.findById(id);
        if (share.isEmpty()) {
            throw new NoSharesAvailableException();
        }
        return share.get();
    }

    @Override
    public Page<Revision<Integer, CompanyShare>> getPrivateDataOfShare(@NonNull Long id, Pageable pageable) {
        Page<Revision<Integer, CompanyShare>> revisions = repository.findRevisions(id, pageable);
        log.info("Revisions of share with {} id are successfully loaded", id);
        if (revisions.isEmpty()) {
            throw new NoRevisionsAvailableException();
        }
        return revisions;
    }


    @Transactional
    @Override
    public List<Page<Revision<Integer, CompanyShare>>> getPrivateDataOfShareByCompany(int usreou, Pageable pageable) {
        List<Page<Revision<Integer, CompanyShare>>> list = new ArrayList<>();
        List<Long> id = repository.findAllIdByUsreou(usreou);
        for (Long aLong : id) {
            list.add(repository.findRevisions(aLong, pageable));
        }
        log.info("Revisions of share with {} id company are successfully loaded", usreou);
        return list;
    }


    @Override
    public Page<CompanyShare> getAllPublicSharesByCompany(int usreou, Pageable pageable) {
        Page<CompanyShare> shares = repository.findAllByUsreou(usreou, pageable);
        if (shares.isEmpty()) {
            throw new NoSharesAvailableException();
        }
        log.info("Shares with {} id company are successfully loaded", usreou);
        return shares;
    }
}
