package com.softserve.clinic.authorizationserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.softserve.clinic.authorizationserver.model.Role;
import com.softserve.clinic.authorizationserver.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {

    public interface New {
    }

    public interface Exist {
    }

    public interface Details {
    }

    public interface AdminDetails {
    }

    public interface Admin {
    }

    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    @JsonView({Details.class, AdminDetails.class})
    private UUID id;

    @NotNull(groups = {New.class})
    @JsonView({Details.class, AdminDetails.class})
    private String username;

    @NotNull(groups = {New.class})
    @JsonView({AdminDetails.class})
    private String password;

    @JsonView({AdminDetails.class})
    private List<String> roles = new ArrayList<>();

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        user.getRoles().stream()
                .map(Role::getName)
                .forEach(roleName -> userDto.getRoles().add(roleName));
        return userDto;
    }

}
