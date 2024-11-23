package com.Rusya2054.bot.web.manager.repositories.management;

import com.Rusya2054.bot.web.manager.models.management.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    Optional<Specialist> findByFullName(String email);
}
