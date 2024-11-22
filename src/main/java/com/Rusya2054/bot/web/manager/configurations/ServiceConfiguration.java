package com.Rusya2054.bot.web.manager.configurations;

import com.Rusya2054.bot.web.manager.factories.AnotherServiceFactory;
import com.Rusya2054.bot.web.manager.services.management.ServicesService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ServiceConfiguration {
    private final ServicesService service;

    @PostConstruct
    private void anotherServiceInitialization(){
        if (!service.getServiceById(1L).isPresent()){
            service.saveService(AnotherServiceFactory.getAnotherService());
        }
    }
}
