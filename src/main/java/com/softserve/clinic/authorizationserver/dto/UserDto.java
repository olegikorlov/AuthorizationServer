package com.softserve.clinic.authorizationserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softserve.clinic.authorizationserver.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
