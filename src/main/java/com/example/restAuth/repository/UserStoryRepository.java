package com.example.restAuth.repository;

import com.example.restAuth.models.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory,Long> {
    void deleteById(Long id);

    List<UserStory> findAll();
}
