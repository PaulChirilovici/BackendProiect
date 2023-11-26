package com.example.backendproiect.service;
import com.example.backendproiect.dao.UserDAO;
import com.example.backendproiect.dto.UserDto;
import com.example.backendproiect.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(email);
        if (user.isPresent()) {
            UserDetails userDetails =
                    org.springframework.security.core.userdetails.User.builder()
                            .username(user.get().getEmail())
                            .password(user.get().getPassword())
                            .build();
            return userDetails;
        } else {
            throw new UsernameNotFoundException("User with this credentials doesn't exists.");
        }
    }

    public User loadUserByEmailAndPassword(String email, String password) throws UsernameNotFoundException {

        Optional<User> user = this.userRepository.findById(email);

        if (user.isPresent() && this.bCryptPasswordEncoder.matches(password, user.get().getPassword()))
            return user.get();
        else
            throw new UsernameNotFoundException("User not found");
    }

    public void saveUser(UserDto userDto,boolean update) throws Exception {
        if (userRepository.findById(userDto.getEmail()).isPresent() && !update)
            throw new Exception("User already exists!");
        this.userRepository.save(new User(userDto.getEmail(), bCryptPasswordEncoder.encode(userDto.getPassword())));
    }
}
