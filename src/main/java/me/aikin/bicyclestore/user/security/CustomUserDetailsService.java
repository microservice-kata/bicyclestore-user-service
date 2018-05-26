package me.aikin.bicyclestore.user.security;

import me.aikin.bicyclestore.user.domain.User;
import me.aikin.bicyclestore.user.exception.ResourceNotFoundException;
import me.aikin.bicyclestore.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
            );
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", userId)
        );

        return UserPrincipal.create(user);
    }
}
