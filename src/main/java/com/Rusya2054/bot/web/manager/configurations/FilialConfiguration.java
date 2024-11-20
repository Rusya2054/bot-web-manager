package com.Rusya2054.bot.web.manager.configurations;

import com.Rusya2054.bot.web.manager.factories.AnotherFilialFactory;
import com.Rusya2054.bot.web.manager.services.management.FilialService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FilialConfiguration {
    private final FilialService filialService;
    @PostConstruct
    private void anotherFilialInitialization(){
        if (!filialService.getFilialById(1L).isPresent()){
            filialService.saveFilial(AnotherFilialFactory.getAnotherFilial());
        }
    }
}
