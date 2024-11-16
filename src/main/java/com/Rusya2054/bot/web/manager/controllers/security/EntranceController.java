package com.Rusya2054.bot.web.manager.controllers.security;

import com.Rusya2054.bot.web.manager.models.security.User;
import com.Rusya2054.bot.web.manager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class EntranceController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model, String error) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login-form";
    }

    @PostMapping("/logout")
    public String logout(){
        User currentUser = userService.getCurrentUser();
        return "redirect:/login";
    }
}
