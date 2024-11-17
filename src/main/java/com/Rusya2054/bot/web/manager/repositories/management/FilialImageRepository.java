package com.Rusya2054.bot.web.manager.repositories.management;

import com.Rusya2054.bot.web.manager.models.management.SpecialistImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialImageRepository extends JpaRepository<SpecialistImage, Long> {
}
