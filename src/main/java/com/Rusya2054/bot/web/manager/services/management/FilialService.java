package com.Rusya2054.bot.web.manager.services.management;

import com.Rusya2054.bot.web.manager.models.management.Filial;
import com.Rusya2054.bot.web.manager.repositories.management.FilialImageRepository;
import com.Rusya2054.bot.web.manager.repositories.management.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FilialService {
    private final FilialRepository filialRepository;
    private final FilialImageRepository filialImageRepository;

    public List<Filial> getFilials(){
        return filialRepository.findAll();
    }
}
