package com.example.toy2.repository;

import com.example.toy2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userid);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByEmail(String email);
}
