package ua.biedin.register.util;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ua.biedin.register.entity.CompanyShare;
import ua.biedin.register.entity.Roles;
import ua.biedin.register.entity.User;
import ua.biedin.register.repository.CompanyShareRepository;
import ua.biedin.register.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
@Slf4j
@ConditionalOnProperty(name = "sharedb", havingValue = "true")
public class DatabaseInitializer implements CommandLineRunner {

    private final UserRepository userRepo;
    private final CompanyShareRepository companyShareRepo;


    @Autowired
    public DatabaseInitializer(UserRepository userRepo, CompanyShareRepository companyShareRepo) {
        this.userRepo = userRepo;
        this.companyShareRepo = companyShareRepo;
    }

    Faker faker = new Faker();

    @Override
    public void run(String... args) {

        companyShareRepo.deleteAll();
        userRepo.deleteAll();

        for (int i = 0; i < 4; i++) {
            CompanyShare share = CompanyShare.builder()
                    .comment(faker.book().genre())
                    .capitalSize(faker.number().numberBetween(1, 6000))
                    .USREOU(faker.number().numberBetween(4000, 9000))
                    .amount(faker.number().randomDigitNotZero())
                    .totalFaceValue(BigDecimal.valueOf(faker.number().randomDouble(2, 5000, 10000)))
                    .faceValue(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 600)))
                    .stateDutyPaid(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 500)))
                    .build();
            companyShareRepo.save(share);
            log.info(share.toString().concat(" successfully inserted!"));
        }

        User admin = User.builder()
                .login(faker.name().username())
                .password(faker.internet().password())
                .roles(List.of(new Roles("ROLE_ADMIN"), new Roles("ROLE_USER")))
                .build();
        userRepo.save(admin);
        log.info("ADMIN successfully inserted - !!!Don't forget to change the value sharedb to FALSE!!!");
        log.info("LOGIN: {} PASSWORD: {} ---- USE IT FOR LOGIN", admin.getLogin(), admin.getPassword());

        User user = User.builder()
                .login(faker.name().username())
                .password(faker.internet().password())
                .roles(List.of(new Roles("ROLE_USER")))
                .build();
        userRepo.save(user);
        log.info("USER successfully inserted - !!!Don't forget to change the value sharedb to FALSE!!!");
        log.info("LOGIN: {} PASSWORD: {} ---- USE IT FOR LOGIN", user.getLogin(), user.getPassword());
    }
}
