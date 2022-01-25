package com.softserve.clinic.authorizationserver.security;

import com.softserve.clinic.authorizationserver.model.User;
import com.softserve.clinic.authorizationserver.security.jwt.JwtUser;
import com.softserve.clinic.authorizationserver.security.jwt.JwtUserFactory;
import com.softserve.clinic.authorizationserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " is not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }

}
