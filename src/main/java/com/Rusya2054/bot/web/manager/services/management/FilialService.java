package com.Rusya2054.bot.web.manager.services.management;

import com.Rusya2054.bot.web.manager.models.management.Filial;
import com.Rusya2054.bot.web.manager.models.management.FilialImage;
import com.Rusya2054.bot.web.manager.repositories.management.FilialImageRepository;
import com.Rusya2054.bot.web.manager.repositories.management.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FilialService {
    private final FilialRepository filialRepository;
    private final FilialImageRepository filialImageRepository;

    public Optional<Filial> getFilialById(Long id){
        return filialRepository.findById(id);
    }

    public List<Filial> getFilials(){
        return filialRepository.findAll();
    }

    public final void saveFilial(Filial filial){
        filialRepository.save(filial);
    }

    public FilialImage toFilialImageEntity(MultipartFile file) throws IOException {
        final FilialImage image = new FilialImage();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}
