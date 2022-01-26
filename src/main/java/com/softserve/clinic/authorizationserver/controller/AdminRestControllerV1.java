package com.softserve.clinic.authorizationserver.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.softserve.clinic.authorizationserver.dto.UserDto;
import com.softserve.clinic.authorizationserver.model.User;
import com.softserve.clinic.authorizationserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;

    @JsonView(UserDto.AdminDetails.class)
    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") UUID id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserDto.fromUser(user);
        return ResponseEntity.ok(result);
    }

    @JsonView(UserDto.AdminDetails.class)
    @PostMapping(value = "/doctors")
    public ResponseEntity<UserDto> registerDoctor(@RequestBody @Validated(value = {UserDto.New.class}) UserDto userDto) {
        User register = userService.registerDoctor(userDto.toUser());
        return ResponseEntity.ok(UserDto.fromUser(register));
    }

}
