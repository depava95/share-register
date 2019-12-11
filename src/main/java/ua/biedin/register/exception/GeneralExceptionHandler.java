package ua.biedin.register.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserLoginNotFoundException.class)
    protected ResponseEntity<GeneralException> handleUserLoginNotFoundException() {
        return new ResponseEntity<>(new GeneralException(LocalDateTime.now(), ("No available user")), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUsernameOrPasswordException.class)
    protected ResponseEntity<GeneralException> handleInvalidUsernameOrPasswordException() {
        return new ResponseEntity<>(new GeneralException(LocalDateTime.now(), ("Invalid username or password")), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoSharesAvailableException.class)
    protected ResponseEntity<GeneralException> handleNoSharesAvailableException() {
        return new ResponseEntity<>(new GeneralException(LocalDateTime.now(), ("There is no shares available")), HttpStatus.FORBIDDEN);
    }

    @Data
    @AllArgsConstructor
    private static class GeneralException {
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime localDateTime;
        private String message;
    }
}







