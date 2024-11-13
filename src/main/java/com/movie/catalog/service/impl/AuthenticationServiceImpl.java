package com.movie.catalog.service.impl;

import com.movie.catalog.repository.RoleRepository;
import com.movie.catalog.repository.UserRepository;
import com.movie.catalog.model.dto.auth.AuthenticationRequest;
import com.movie.catalog.model.dto.auth.AuthenticationResponse;
import com.movie.catalog.model.dto.auth.RegisterRequest;
import com.movie.catalog.model.entity.Role;
import com.movie.catalog.model.entity.User;
import com.movie.catalog.model.entity.enums.RoleEnum;
import com.movie.catalog.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("email is already in use");

        String password = passwordEncoder.encode(request.getPassword());
        Role userRole = roleRepository.findByName(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("role does not exist"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = User.builder()
                .email(request.getEmail())
                .password(password)
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .roles(roles)
                .build();


        userRepository.save(user);

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );
        String token = jwtService.generateToken(authentication);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        log.info("S");
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );
        log.info("SS");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("SS2");
        String token = jwtService.generateToken(authentication);

        log.info("SS3");
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
