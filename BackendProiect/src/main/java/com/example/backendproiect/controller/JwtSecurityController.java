package com.example.backendproiect.controller;

import com.example.backendproiect.auth.JwtUtil;
import com.example.backendproiect.dto.LoginReq;
import com.example.backendproiect.dto.LoginRes;
import com.example.backendproiect.dto.UserDto;
import com.example.backendproiect.entities.User;
import com.example.backendproiect.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableWebSecurity
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class JwtSecurityController {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq) {
        //System.out.println(loginReq.getEmail());
        //System.out.println(loginReq.getPassword());
        try {
            User userDetails = userDetailsService.loadUserByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword());
            UserDto user = new UserDto(userDetails.getEmail(), "");
            String token = jwtUtil.generateToken(user);
            LoginRes loginRes = new LoginRes(user.getEmail(), token);
            return ResponseEntity.ok(loginRes);
        } catch (Exception e) {   //e.printStackTrace();
            return ResponseEntity.badRequest().body("User not found..");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
        try {
            userDetailsService.saveUser(user);
            return ResponseEntity.ok(user.toString() + " REGISTERED SUCCESSFULLY!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User already registered!");
        }
    }
}
