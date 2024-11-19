package com.Rusya2054.bot.web.manager.controllers.security;

import com.Rusya2054.bot.web.manager.models.security.User;
import com.Rusya2054.bot.web.manager.models.security.enums.Role;
import com.Rusya2054.bot.web.manager.services.UserService;
import com.Rusya2054.bot.web.manager.services.bot.BotLoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminControllers {
    private final UserService userService;
    private final BotLoggerService botLoggerService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if (!userService.deleteUser(id)){
            redirectAttributes.addFlashAttribute("message", "Ошибка с удалением");
        }
        return "redirect:/admin/";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable final Long id,
                             @RequestBody final Map<String, Object> requestData,
                             RedirectAttributes redirectAttributes){
        final Role role = Role.valueOf((String) requestData.get("role"));
        User user = userService.getUserById(id).orElse(new User());
        user.setRole(role);
        if (!userService.updateUser(user)){
            redirectAttributes.addFlashAttribute("message", "Ошибка с обновлением данных");
        }
        return "redirect:/admin/";
    }
    // TODO: добавить логи бота
    // TODO: перезапуск бота (отражать старт/стоп это в логах)
    // TODO: логги по кнопке лучше, т.к. String тяжелый List<BotLogger> loggers = botLoggerService.getAllLogs();
    // TODO: анимация появлений

    @GetMapping("/")
    public String getAdminPanel(Model model){
        List<User> users = userService.getUsers();
        User currentUser = userService.getCurrentUser();
        users.remove(currentUser);
        model.addAttribute("users", users);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("roles", List.of(Role.ROLE_USER, Role.ROLE_MODER, Role.ROLE_ADMIN));
        return "admin-panel";
    }
    @PostMapping("/add-user")
    public String getAdminPanel(User user){
        userService.createUser(user);
        return "redirect:/admin/";
    }
}
