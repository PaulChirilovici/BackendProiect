package com.example.backendproiect.controller;

import com.example.backendproiect.dto.LoginReq;
import com.example.backendproiect.dto.LoginRes;
import com.example.backendproiect.dto.UserDto;
import com.example.backendproiect.entities.User;
import com.example.backendproiect.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class JwtSecurityController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq) {
//        System.out.println(loginReq.getEmail());
//        System.out.println(loginReq.getPassword());
        try {
            User userDetails = userDetailsService.loadUserByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword());
            UserDto user = new UserDto(userDetails.getEmail(), "");

            LoginRes loginRes = new LoginRes(user.getEmail());
            return ResponseEntity.ok(loginRes);
        } catch (Exception e) {   //e.printStackTrace();
            return ResponseEntity.badRequest().body("User not found..");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
        try {
            userDetailsService.saveUser(user,false);
            return ResponseEntity.ok(user.toString() + " REGISTERED SUCCESSFULLY!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User already registered!");
        }
    }
}
