package ua.biedin.register.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserLoginDTO {

    @NotBlank
    private String login;
    @Size(min = 6, message = "Minimum 6 symbols")
    @NotBlank
    private String password;

}
