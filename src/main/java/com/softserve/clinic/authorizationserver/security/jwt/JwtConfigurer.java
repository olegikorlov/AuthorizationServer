package com.softserve.clinic.authorizationserver.security.jwt;

import com.softserve.clinic.authorizationserver.exception.FilterChainExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
@RequiredArgsConstructor
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;
    private final FilterChainExceptionHandler filterChainExceptionHandler;

    @Override
    public void configure(HttpSecurity httpSecurity) {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(filterChainExceptionHandler, JwtTokenFilter.class);
    }

}
