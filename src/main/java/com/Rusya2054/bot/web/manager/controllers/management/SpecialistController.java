package com.Rusya2054.bot.web.manager.controllers.management;

import com.Rusya2054.bot.web.manager.models.management.Specialist;
import com.Rusya2054.bot.web.manager.models.management.SpecialistImage;
import com.Rusya2054.bot.web.manager.services.management.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.*;

@Controller
@RequiredArgsConstructor
@SessionAttributes("{message}")
@RequestMapping("/specialist")
@PreAuthorize("hasRole('ADMIN') || hasRole('MODER')")
public class SpecialistController {
    private final SpecialistService specialistService;

    @GetMapping
    public String specialistsPage(Model model){
        List<Specialist> specialists = specialistService.getAll();
        specialists.sort(new Comparator<Specialist>() {
            @Override
            public int compare(Specialist o1, Specialist o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });
        model.addAttribute("activeSpecialists", specialists.stream().filter(s->s.getIsActive().equals(true)).toList());
        model.addAttribute("nonActiveSpecialists", specialists.stream().filter(s->s.getIsActive().equals(false)).toList());
        return "specialists";
    }

    @PostMapping("/add")
    public String addSpecialist(MultipartFile file, Specialist specialist, RedirectAttributes redirectAttributes) throws IOException {

        if (!file.getOriginalFilename().isEmpty() && (Objects.equals(file.getContentType(), "image/png") ||
                Objects.equals(file.getContentType(), "image/jpeg")|| Objects.equals(file.getContentType(), "image/jpg"))){
            SpecialistImage specialistImage = specialistService.toSpecialistImageEntity(file);
            specialist.setImage(specialistImage);
        }

        if (!file.getOriginalFilename().isEmpty()){
            SpecialistImage specialistImage = specialistService.toSpecialistImageEntity(file);
            specialist.setImage(specialistImage);
        }
        if (!specialistService.createSpecialist(specialist)){
            redirectAttributes.addFlashAttribute("message", "Пользователь уже существует");
        }
        return "redirect:/specialist";
    }

    @PostMapping("/update")
    public String updateSpecialist(MultipartFile file, Specialist specialist, Model model) throws IOException {
        if (!file.getOriginalFilename().isEmpty()){
            SpecialistImage specialistImage = specialistService.toSpecialistImageEntity(file);
            specialist.setImage(specialistImage);
        }
        Specialist updatedSpecialist = specialistService.updateSpecialist(specialist);

        model.addAttribute("specialist", updatedSpecialist);
        if (updatedSpecialist.getImage() != null){
            model.addAttribute("specialistImage", Base64.getEncoder().encodeToString(updatedSpecialist.getImage().getBytes()));
        }
        return "redirect:/specialist/"+specialist.getId();
    }

    @GetMapping("/{id}")
    public String getSpecialist(@PathVariable Long id, Model model){
        // TODO: найти все сервисы где задействован специалист
        try {
            Specialist specialist = specialistService.getSpecialistById(id).orElseThrow();
            model.addAttribute("specialist", specialist);
            if (specialist.getImage() != null){
                model.addAttribute("specialistImage", Base64.getEncoder().encodeToString(specialist.getImage().getBytes()));
            }
            return "specialist-info";
        } catch (NoSuchElementException noSuchElementException){
            return "redirect:/specialist";
        }
    }

    @PostMapping("/update/archive")
    public String updateSpecialist(@RequestBody Map<Object, String> request, RedirectAttributes redirectAttributes){
        final Map<String, Boolean> actionMap = Map.of("setActive", true, "setNonActive", false);
        final Long id = Long.parseLong(request.get("id"));
        final String action = (String) request.get("action");
        if (specialistService.getSpecialistById(id).isPresent() && actionMap.containsKey(action)){
            final Specialist specialist = specialistService.getSpecialistById(id).get();
            specialist.setIsActive(actionMap.get(action));
            specialistService.updateSpecialist(specialist);
            return "redirect:/specialist/"+specialist.getId();
        }
        return "redirect:/specialist";
    }


}
