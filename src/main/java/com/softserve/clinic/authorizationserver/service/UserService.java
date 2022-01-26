package com.softserve.clinic.authorizationserver.service;

import com.softserve.clinic.authorizationserver.model.User;

import java.util.List;
import java.util.UUID;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
public interface UserService {

    User registerUser(User user);

    User registerDoctor(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(UUID id);

    void delete(UUID id);

}
