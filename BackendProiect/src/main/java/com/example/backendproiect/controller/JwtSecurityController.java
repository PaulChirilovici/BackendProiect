package com.example.backendproiect.controller;

import com.example.backendproiect.auth.JwtUtil;
import com.example.backendproiect.dto.LoginReq;
import com.example.backendproiect.dto.LoginRes;
import com.example.backendproiect.dto.UserDto;
import com.example.backendproiect.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableWebSecurity
@RequestMapping("/auth")
public class JwtSecurityController {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq) {
        System.out.println(loginReq.getEmail());
        System.out.println(loginReq.getPassword());
        try {
            UserDetails userDetails=userDetailsService.loadUserByUsername(loginReq.getEmail());
            UserDto user = new UserDto(userDetails.getUsername(), "");
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(user.getEmail(), token);
            return ResponseEntity.ok(loginRes);
        }catch (Exception e)
        {   //e.printStackTrace();
            return ResponseEntity.badRequest().body("User not found..");
        }

    }
}
