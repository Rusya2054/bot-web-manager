package com.Rusya2054.bot.web.manager.controllers.management;

import com.Rusya2054.bot.web.manager.models.management.Specialist;
import com.Rusya2054.bot.web.manager.services.management.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/specialist")
@PreAuthorize("hasRole('ADMIN') || hasRole('MODER')")
public class SpecialistController {
    private final SpecialistService specialistService;

    @GetMapping
    public String specialistsPage(){
        System.out.println("specialistsPage");
        return "specialists";
    }

    @PostMapping("/add")
    public void addSpecialist(Specialist specialist){
        System.out.println(specialist);
    }
    @GetMapping("/{id}")
    public void getSpecialist(@PathVariable Long id){
        System.out.println(id);
        System.out.println(specialistService.getSpecialistById(id));
    }

    @PostMapping("/update?{id}")
    public void updateSpecialist(Specialist specialist){
        System.out.println(specialist);
        // TODO: добавить валидацию
    }

}
