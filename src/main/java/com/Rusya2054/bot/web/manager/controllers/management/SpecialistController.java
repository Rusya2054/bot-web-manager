package com.Rusya2054.bot.web.manager.controllers.management;

import com.Rusya2054.bot.web.manager.models.management.Specialist;
import com.Rusya2054.bot.web.manager.models.management.SpecialistImage;
import com.Rusya2054.bot.web.manager.services.management.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/specialist")
@PreAuthorize("hasRole('ADMIN') || hasRole('MODER')")
public class SpecialistController {
    private final SpecialistService specialistService;

    @GetMapping
    public String specialistsPage(Model model){
        model.addAttribute("specialists", specialistService.getAll());
        return "specialists";
    }

    @PostMapping("/add")
    public String addSpecialist(MultipartFile file, Specialist specialist) throws IOException {
        if (!file.getOriginalFilename().isEmpty()){
            SpecialistImage specialistImage = specialistService.toSpecialistImageEntity(file);
            specialist.setImage(specialistImage);
        }
        specialistService.createSpecialist(specialist);
        return "redirect:/specialist";
    }

    @GetMapping("/{id}")
    public String getSpecialist(@PathVariable Long id, Model model){
        try {
            Specialist specialist = specialistService.getSpecialistById(id).orElseThrow();
            model.addAttribute("specialist", specialist);
            return "specialist-info";
        } catch (NoSuchElementException noSuchElementException){
            return "redirect:/specialist";
        }
    }

    @PostMapping("/update?{id}")
    public void updateSpecialist(Specialist specialist){
        System.out.println(specialist);
        // TODO: добавить валидацию
    }

}
