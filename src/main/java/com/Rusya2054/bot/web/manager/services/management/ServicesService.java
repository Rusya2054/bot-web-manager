package com.Rusya2054.bot.web.manager.services.management;

import com.Rusya2054.bot.web.manager.models.management.Specialist;
import com.Rusya2054.bot.web.manager.repositories.management.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServicesService {
    private final ServiceRepository serviceRepository;

    public List<com.Rusya2054.bot.web.manager.models.management.Service> getServices(){
        return serviceRepository.findAll();
    }
    public List<com.Rusya2054.bot.web.manager.models.management.Service> getServicesBySpecialist(Specialist specialist){
        return serviceRepository.findBySpecialistId(specialist.getId());
    }
    public List<com.Rusya2054.bot.web.manager.models.management.Service> getServicesBySpecialist(Long id){
        return serviceRepository.findBySpecialistId(id);
    }

//    public List<Service> get
}
