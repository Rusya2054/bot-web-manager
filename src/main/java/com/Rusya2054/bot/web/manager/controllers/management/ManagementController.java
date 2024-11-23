package com.Rusya2054.bot.web.manager.controllers.management;

import com.Rusya2054.bot.web.manager.models.management.Filial;
import com.Rusya2054.bot.web.manager.services.UserService;
import com.Rusya2054.bot.web.manager.services.management.FilialService;
import com.Rusya2054.bot.web.manager.services.management.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') || hasRole('MODER')")
@RequestMapping("/")
public class ManagementController {
    private final UserService userService;
    private final FilialService filialService;
    private final SpecialistService specialistService;

    @GetMapping("")
    public String getMainPage(Model model){
        model.addAttribute("currentUser", userService.getCurrentUser());
        List<Filial> filials = filialService.getFilials();
        System.out.println(filials);
        model.addAttribute("filials", filials);
        return "main";
    }

    @GetMapping("/manage")
    public String getManagePage(Model model){
        List<Filial> filials = filialService.getFilials();
        return "manage-panel";
    }
}
