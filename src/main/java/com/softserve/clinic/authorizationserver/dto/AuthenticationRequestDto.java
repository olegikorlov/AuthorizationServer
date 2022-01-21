package com.softserve.clinic.authorizationserver.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
@Value
public class AuthenticationRequestDto implements Serializable {

    String username;
    String password;

}
