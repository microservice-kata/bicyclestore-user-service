package me.aikin.bicyclestore.user.service;

import me.aikin.bicyclestore.user.domain.Role;
import me.aikin.bicyclestore.user.domain.RoleName;
import me.aikin.bicyclestore.user.domain.User;
import me.aikin.bicyclestore.user.exception.AppException;
import me.aikin.bicyclestore.user.repository.RoleRepository;
import me.aikin.bicyclestore.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public String getUserName() {
        return "aikin";
    }

    public User createUser(User newUser) {
        Role userRole = roleRepository
            .findByName(RoleName.ROLE_USER)
            .orElseThrow(() -> new AppException("User Role not set."));
        newUser.setRoles(Collections.singleton(userRole));
        return userRepository.saveAndFlush(newUser);
    }
}
