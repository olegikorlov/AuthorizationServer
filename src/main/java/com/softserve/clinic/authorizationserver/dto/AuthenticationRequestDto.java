package com.softserve.clinic.authorizationserver.dto;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
@Value
public class AuthenticationRequestDto implements Serializable {

    @NotBlank
    @Email(message = "Email should be valid")
    String username;

    @NotBlank
    String password;

}
