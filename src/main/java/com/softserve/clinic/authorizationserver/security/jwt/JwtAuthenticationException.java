package com.softserve.clinic.authorizationserver.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

}
