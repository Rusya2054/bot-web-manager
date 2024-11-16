package com.Rusya2054.bot.web.manager.services;

import com.Rusya2054.bot.web.manager.models.security.User;
import com.Rusya2054.bot.web.manager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean deleteUser(Long id){
        if (!userRepository.findById(id).isPresent()) return false;
        userRepository.deleteById(id);
        log.info("Deleted User with id: {}", id);
        return true;
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public boolean updateUser(User user){
        if (user.getUsername() != null){
            userRepository.save(user);
            log.info("Update information about User with id: {}", user.getId());
            return true;
        }
        return false;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow();
    }

    public boolean createUser(User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent()) return false;
        user.setOriginalPassword(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new User: {} Role: {}", user.getUsername(), user.getRole());
        userRepository.save(user);
        return true;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

}
