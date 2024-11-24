package com.Rusya2054.bot.web.manager.controllers.management;

import com.Rusya2054.bot.web.manager.controllers.management.request.UpdateServiceData;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequiredArgsConstructor
@SessionAttributes("{message}")
@RequestMapping("/")
@PreAuthorize("hasRole('ADMIN') || hasRole('MODER')")
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
    public String updateService(@RequestBody UpdateServiceData updateServiceData, RedirectAttributes redirectAttributes){
        if (updateServiceData.getServiceName().isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Пустое название услуги");
            return "redirect:/manage";
        }
        if (updateServiceData.getServiceId() != null &&
                updateServiceData.getFilialId() != null &&
                updateServiceData.getSpecialistId() != null){
            Service service = (updateServiceData.getIsNew()) ? new Service() : servicesService.getServiceById(updateServiceData.getServiceId()).get();
            Optional<Filial> filial = filialService.getFilialById(updateServiceData.getFilialId());
            if (filial.isPresent() && service.getFilial() != null && !service.getFilial().getId().equals(updateServiceData.getFilialId())){
                service.setFilial(filialService.getFilialById(updateServiceData.getFilialId()).get());
            }
            if (filial.isPresent() && service.getFilial() == null){
                service.setFilial(filial.get());
            }
            Optional<Specialist> specialist = specialistService.getSpecialistById(updateServiceData.getSpecialistId());
            if (specialist.isPresent() && service.getSpecialist() != null && !service.getSpecialist().getId().equals(updateServiceData.getSpecialistId())){
                service.setSpecialist(specialistService.getSpecialistById(updateServiceData.getSpecialistId()).get());
            }
            if (specialist.isPresent() && service.getSpecialist() == null){
                service.setSpecialist(specialist.get());
            }
            service.setName(updateServiceData.getServiceName());
            servicesService.saveService(service);
            return "redirect:/manage";
        }
        redirectAttributes.addFlashAttribute("message", "Данные не полностью заполнены");
        return "redirect:/manage";
    }

    @PostMapping("/service/delete")
    public String deleteService(@RequestBody Map<String, Object> requestData){
        System.out.println(requestData);
        final Long id = Long.parseLong((String) requestData.get("serviceId"));
        servicesService.deleteServiceById(id);
        return "redirect:/manage";
    }

}
