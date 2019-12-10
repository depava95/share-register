package ua.biedin.register;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.biedin.register.entity.User;
import ua.biedin.register.service.UserService;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class RegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterApplication.class, args);
    }
}
