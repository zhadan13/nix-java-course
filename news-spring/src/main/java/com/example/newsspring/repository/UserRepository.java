package com.example.newsspring.repository;

import com.example.newsspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User AS u WHERE u.email = ?1")
    Optional<User> findUserByEmail(final String email);

    @Query("SELECT u FROM User AS u WHERE u.login = ?1")
    Optional<User> findUserByLogin(final String login);
}
