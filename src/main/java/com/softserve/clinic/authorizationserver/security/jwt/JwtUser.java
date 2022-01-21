package com.softserve.clinic.authorizationserver.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
@AllArgsConstructor
@Getter
public class JwtUser implements UserDetails {

    private final UUID id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final boolean enabled;
    private final LocalDateTime lastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
