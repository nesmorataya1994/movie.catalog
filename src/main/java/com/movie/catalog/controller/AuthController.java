package com.movie.catalog.controller;

import com.movie.catalog.model.dto.auth.AuthenticationRequest;
import com.movie.catalog.model.dto.auth.AuthenticationResponse;
import com.movie.catalog.model.dto.auth.RegisterRequest;
import com.movie.catalog.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationServiceImpl userServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(userServiceImpl.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse response = userServiceImpl.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
