package com.univerity.hallmanagement.repository;

import com.univerity.hallmanagement.entity.UniversityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityUserRepository extends JpaRepository<UniversityUser, Long> {
    Optional<UniversityUser> findByUsername(String username);
}
