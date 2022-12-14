package com.example.restAuth.repository;

import com.example.restAuth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {


    Optional<User> findByUsernameIgnoreCase(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
