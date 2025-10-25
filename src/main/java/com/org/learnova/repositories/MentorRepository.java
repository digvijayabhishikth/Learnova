package com.org.learnova.repositories;

import com.org.learnova.entities.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    boolean existsByEmail(String email);

    Optional<Mentor> findByEmail(String email);
}
