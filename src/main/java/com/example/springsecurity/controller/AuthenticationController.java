package com.example.springsecurity.controller;

import com.example.springsecurity.config.JwtUtil;
import com.example.springsecurity.dao.UserDao;
import com.example.springsecurity.dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails service = userDao.findUserByEmail(request.getEmail());
        if (service != null){
            return ResponseEntity.ok(jwtUtil.generateToken(service));
        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }
}
