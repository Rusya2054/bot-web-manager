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

    public boolean deleteUser(Long id){
        // TODO: тут ошибка при удалении
        if (userRepository.findById(id).isPresent()) return false;
        userRepository.deleteById(id);
        return true;
    }


    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow();
    }

    public boolean createUser(User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent()) return false;
        System.out.println("registry password: '" + user.getPassword() + "'");
        user.setOriginalPassword(user.getPassword());
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
