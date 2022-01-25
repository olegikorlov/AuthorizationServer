package com.softserve.clinic.authorizationserver.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.softserve.clinic.authorizationserver.dto.AdminUserDto;
import com.softserve.clinic.authorizationserver.dto.UserDto;
import com.softserve.clinic.authorizationserver.model.User;
import com.softserve.clinic.authorizationserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") UUID id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @JsonView(UserDto.AdminDetails.class)
    @PostMapping(value = "/doctors")
    public ResponseEntity<?> saveUser(@RequestBody @Validated(value = {UserDto.New.class, UserDto.Admin.class}) UserDto userDto) {
        User register = userService.registerDoctor(userDto.toUser());
        return new ResponseEntity<>(UserDto.fromUser(register), HttpStatus.OK);
    }
}