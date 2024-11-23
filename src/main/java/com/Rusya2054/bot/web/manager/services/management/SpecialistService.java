package com.Rusya2054.bot.web.manager.services.management;

import com.Rusya2054.bot.web.manager.models.management.Specialist;
import com.Rusya2054.bot.web.manager.models.management.SpecialistImage;
import com.Rusya2054.bot.web.manager.repositories.management.SpecialistImageRepository;
import com.Rusya2054.bot.web.manager.repositories.management.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialistService {
    private static final Logger log = LoggerFactory.getLogger(SpecialistService.class);
    private final SpecialistRepository specialistRepository;
    private final SpecialistImageRepository specialistImageRepository;

    public Optional<Specialist> getSpecialistById(Long id){
        return specialistRepository.findById(id);
    }
    public List<Specialist> getAll(){
        return specialistRepository.findAll();
    }

    public SpecialistImage toSpecialistImageEntity(MultipartFile file) throws IOException {
        final SpecialistImage image = new SpecialistImage();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public Specialist updateSpecialist(Specialist specialist){
        Optional<Specialist> dbSpecialist = specialistRepository.findByFullName(specialist.getFullName());
        if (dbSpecialist.isPresent()){
            specialist.setId(dbSpecialist.get().getId());
            if (specialist.getImage() == null && dbSpecialist.get().getImage() != null){
                specialist.setImage(dbSpecialist.get().getImage());
            }
            log.info("Updated information of specialist with id: {}", specialist.getId());
            return specialistRepository.save(specialist);
        }
        createSpecialist(specialist);
        return specialistRepository.findByFullName(specialist.getFullName()).get();
    }

    public boolean createSpecialist(Specialist specialist) {
        Optional<Specialist> dbSpecialist = specialistRepository.findByFullName(specialist.getFullName());
        if (!dbSpecialist.isPresent()){
            specialist.setIsActive(true);
            specialistRepository.save(specialist);
            log.info("Created new specialist with fullName: {}", specialist.getFullName());
            return true;
        }
        return false;
    }
}
