package com.Rusya2054.bot.web.manager.controllers.management;

import com.Rusya2054.bot.web.manager.models.management.Filial;
import com.Rusya2054.bot.web.manager.models.management.Service;
import com.Rusya2054.bot.web.manager.models.management.Specialist;
import com.Rusya2054.bot.web.manager.services.UserService;
import com.Rusya2054.bot.web.manager.services.management.FilialService;
import com.Rusya2054.bot.web.manager.services.management.ServicesService;
import com.Rusya2054.bot.web.manager.services.management.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') || hasRole('MODER')")
@RequestMapping("/")
public class ManagementController {
    private final UserService userService;
    private final FilialService filialService;
    private final ServicesService servicesService;
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
        final Comparator<String> nameComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        List<Filial> filials = new ArrayList<>(filialService.getFilials().stream().filter(f->f.getName() != null).toList());
        filials.sort((s1, s2) ->nameComparator.compare(s1.getName(), s2.getName()));

        List<Service> services = new ArrayList<>(servicesService.getServices().stream().filter(s->s.getName()!=null).toList());
        services.sort((s1, s2) ->nameComparator.compare(s1.getName(), s2.getName()));

        List<Specialist> specialists = new ArrayList<>(specialistService.getAll().stream().filter(s->s.getFullName() != null).toList());
        specialists.sort((s1, s2) ->nameComparator.compare(s1.getFullName(), s2.getFullName()));

        model.addAttribute("filials", filials);
        model.addAttribute("services", services);
        model.addAttribute("specialists", specialists);
        return "manage-panel";
    }
    @PostMapping("/service/update")
    public String updateService(@RequestBody Map<String, Object> requestData){
        System.out.println(requestData);
        return "redirect:/manage";
    }

    @PostMapping("/service/delete")
    public String deleteService(@RequestBody Map<String, Object> requestData){
        System.out.println(requestData);
        return "redirect:/manage";
    }

}
