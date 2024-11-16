package com.Rusya2054.bot.web.manager.services;

import com.Rusya2054.bot.web.manager.models.security.User;
import com.Rusya2054.bot.web.manager.models.security.enums.Role;
import com.Rusya2054.bot.web.manager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createSimpleUser(User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent()) return false;
        System.out.println("registry password: '" + user.getPassword() + "'");
        // шифруем пароль
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("registry new password: '" + user.getPassword() + "'");
        user.setRole(Role.ROLE_USER);
        log.info("Saving new User as simple {}", user.getUsername());
        userRepository.save(user);
        return true;
    }
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        // TODO: тут подумать нужно
        return userRepository.findByUsername(username).orElseThrow();
    }

    public boolean createUser(User user){
        // TODO: проверить isPresent()
        if (userRepository.findByUsername(user.getUsername()).isPresent()) return false;
        System.out.println("registry password: '" + user.getPassword() + "'");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("registry new password: '" + user.getPassword() + "'");
        log.info("Saving new User: {} Role: {}", user.getUsername(), user.getRole());
        userRepository.save(user);
        return true;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

}
