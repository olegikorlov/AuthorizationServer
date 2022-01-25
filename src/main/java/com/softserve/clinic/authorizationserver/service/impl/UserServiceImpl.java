package com.softserve.clinic.authorizationserver.service.impl;

import com.softserve.clinic.authorizationserver.model.Role;
import com.softserve.clinic.authorizationserver.model.Status;
import com.softserve.clinic.authorizationserver.model.User;
import com.softserve.clinic.authorizationserver.repository.RoleRepository;
import com.softserve.clinic.authorizationserver.repository.UserRepository;
import com.softserve.clinic.authorizationserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        return register(user, "ROLE_PATIENT");
    }

    @Override
    public User registerDoctor(User user) {
        return register(user, "ROLE_DOCTOR");
    }

    private User register(User user, String roleName) {
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        log.info("IN register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} result found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(UUID id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("IN findById - user with id: {} is not found", id);
            return null;
        }
        log.info("IN findByUsername - user: {} is found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} was delete successfully", id);
    }
}
