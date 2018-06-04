package me.aikin.bicyclestore.user.repository;

import me.aikin.bicyclestore.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findById(Long userId);

    Boolean existsByUsername(String username);
}
