package com.Rusya2054.bot.web.manager.services;

import com.Rusya2054.bot.web.manager.models.security.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.Rusya2054.bot.web.manager.repositories.UserRepository;

@Data
@Service
public class ApplicationUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println(username);
//        System.out.println(userRepository.findByUsername(username));
//        System.out.println(userRepository.findAll());
        return userRepository.findByUsername(username).orElse(new User());
    }
}
