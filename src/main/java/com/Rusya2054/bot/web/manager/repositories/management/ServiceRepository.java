package com.Rusya2054.bot.web.manager.repositories.management;

import com.Rusya2054.bot.web.manager.models.management.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query(value = "SELECT * FROM services WHERE specialist_id = :specialistId", nativeQuery = true)
    List<Service> findBySpecialistId(Long specialistId);
}
