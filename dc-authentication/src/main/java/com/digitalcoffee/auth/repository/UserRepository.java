package com.digitalcoffee.auth.repository;

import com.digitalcoffee.auth.entity.UserRoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserRoot, String> {
    boolean existsByUsername(String username);
    Optional<UserRoot> findByUsername(String username);
}
