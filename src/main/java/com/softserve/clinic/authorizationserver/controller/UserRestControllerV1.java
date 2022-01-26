package com.softserve.clinic.authorizationserver.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.softserve.clinic.authorizationserver.dto.UserDto;
import com.softserve.clinic.authorizationserver.model.User;
import com.softserve.clinic.authorizationserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/users")
@Slf4j
public class UserRestControllerV1 {

    private final UserService userService;

    @JsonView(UserDto.Details.class)
    @GetMapping(value = "/me")
    public ResponseEntity<UserDto> getUserById(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserDto.fromUser(user);
        return ResponseEntity.ok(result);
    }

}
