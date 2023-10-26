package com.example.backendproiect.controller;

import com.example.backendproiect.auth.JwtUtil;
import com.example.backendproiect.dto.LoginReq;
import com.example.backendproiect.dto.LoginRes;
import com.example.backendproiect.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class JwtSecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginRes> login(@RequestBody LoginReq loginReq) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = authentication.getName();
            UserDto user = new UserDto(email, "");
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(email, token);
            return ResponseEntity.ok(loginRes);
        }catch (Exception e)
        {
            return ResponseEntity.badRequest().body(new LoginRes("failed","failed"));
        }

    }
}
