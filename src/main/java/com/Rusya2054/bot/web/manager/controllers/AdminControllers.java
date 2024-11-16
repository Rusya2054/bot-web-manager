package com.Rusya2054.bot.web.manager.controllers;

import com.Rusya2054.bot.web.manager.models.security.User;
import com.Rusya2054.bot.web.manager.models.security.enums.Role;
import com.Rusya2054.bot.web.manager.repositories.UserRepository;
import com.Rusya2054.bot.web.manager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminControllers {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        System.out.println(id);
        userService.deleteUser(id);
    }

    @GetMapping("/")
    public String getAdminPanel(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("currentUser", userService.getCurrentUser());
        model.addAttribute("roles", List.of(Role.ROLE_USER, Role.ROLE_MODER, Role.ROLE_ADMIN));
        return "admin-panel";
    }
    @PostMapping("/add-user")
    public String getAdminPanel(User user){
        System.out.println(user);
        System.out.println(user.getRole().getClass());
        userService.createUser(user);
        return "redirect:/admin/";
    }
}
