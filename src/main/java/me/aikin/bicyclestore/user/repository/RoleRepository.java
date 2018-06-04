package me.aikin.bicyclestore.user.repository;

import me.aikin.bicyclestore.user.domain.Role;
import me.aikin.bicyclestore.user.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
