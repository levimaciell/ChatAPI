package dev.levimaciell.chatAPI.user.repository;

import dev.levimaciell.chatAPI.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByUsername(String username);

    User getReferenceByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
