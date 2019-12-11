package ua.biedin.register.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserLoginNotFoundException.class)
    protected ResponseEntity<GeneralException> handleUserLoginNotFoundException() {
        return new ResponseEntity<>(new GeneralException(("No available user")), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUsernameOrPasswordException.class)
    protected ResponseEntity<GeneralException> handleInvalidUsernameOrPasswordException() {
        return new ResponseEntity<>(new GeneralException(("Invalid username or password")), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoSharesAvailableException.class)
    protected ResponseEntity<GeneralException> handleNoSharesAvailableException() {
        return new ResponseEntity<>(new GeneralException(("There is no shares available")), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaveOrUpdateShareException.class)
    protected ResponseEntity<GeneralException> handleSaveOrUpdateShareException() {
        return new ResponseEntity<>(new GeneralException(("Error save share")), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    private static class GeneralException {
        private String message;
    }
}







