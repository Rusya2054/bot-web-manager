package com.Rusya2054.bot.web.manager.controllers.management;

import com.Rusya2054.bot.web.manager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') || hasRole('MODER')")
@RequestMapping("/")
public class ManagementController {
    private final UserService userService;
    @GetMapping("/main")
    public String getMainPage(Model model){
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "main";
    }
}
