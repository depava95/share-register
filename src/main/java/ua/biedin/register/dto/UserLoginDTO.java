package ua.biedin.register.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserLoginDTO {

    @NotBlank
    private String login;
    @Size(min = 6, message = "Minimum 6 symbols")
    @NotBlank
    private String password;

}
