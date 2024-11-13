package com.movie.catalog.service;

import com.movie.catalog.model.dto.auth.AuthenticationRequest;
import com.movie.catalog.model.dto.auth.AuthenticationResponse;
import com.movie.catalog.model.dto.auth.RegisterRequest;

public interface IAuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
