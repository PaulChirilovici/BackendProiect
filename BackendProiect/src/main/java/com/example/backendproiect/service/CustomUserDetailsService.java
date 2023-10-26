package com.example.backendproiect.service;

import com.example.backendproiect.dao.UserDAO;
import com.example.backendproiect.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(email);
        if(user.isPresent())
        {UserDetails userDetails =
                    org.springframework.security.core.userdetails.User.builder()
                            .username(user.get().getEmail())
                            .password(user.get().getPassword())
                            .build();
            return userDetails;
        }else{
            throw new UsernameNotFoundException("User with this credentials doesn't exists.");
        }
    }
    public UserDetails loadUserByEmailAndPassword(String email) throws UsernameNotFoundException {
        //TODO
        return null;
    }
}
