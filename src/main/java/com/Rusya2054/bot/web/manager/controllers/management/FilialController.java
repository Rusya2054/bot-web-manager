package com.Rusya2054.bot.web.manager.controllers.management;

import com.Rusya2054.bot.web.manager.models.management.Filial;
import com.Rusya2054.bot.web.manager.models.management.FilialImage;
import com.Rusya2054.bot.web.manager.services.management.FilialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/filial")
public class FilialController {
    private final FilialService filialService;

    @PostMapping("/add")
    public String addFilial(MultipartFile file, Filial filial, RedirectAttributes redirectAttributes) throws IOException {
        if (!file.getOriginalFilename().isEmpty() && (Objects.equals(file.getContentType(), "image/png") ||
                Objects.equals(file.getContentType(), "image/jpeg")|| Objects.equals(file.getContentType(), "image/jpg"))){
            FilialImage filialImage = filialService.toFilialImageEntity(file);
            filial.setImage(filialImage);
        }
        return "manage-panel";
    }
}
