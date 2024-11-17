package com.Rusya2054.bot.web.manager.services.management;

import com.Rusya2054.bot.web.manager.models.management.Specialist;
import com.Rusya2054.bot.web.manager.repositories.management.SpecialistImageRepository;
import com.Rusya2054.bot.web.manager.repositories.management.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialistService {
    private final SpecialistRepository specialistRepository;
    private final SpecialistImageRepository specialistImageRepository;

    public Optional<Specialist> getSpecialistById(Long id){
        return specialistRepository.findById(id);
    }
    public List<Specialist> getAll(){
        return specialistRepository.findAll();
    }
}
