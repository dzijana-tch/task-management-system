package com.charniuk.taskmanagementsystem.service.impl;

import com.charniuk.taskmanagementsystem.dto.request.SignInRequest;
import com.charniuk.taskmanagementsystem.dto.request.SignUpRequest;
import com.charniuk.taskmanagementsystem.dto.response.JwtAuthenticationResponse;
import com.charniuk.taskmanagementsystem.enums.Role;
import com.charniuk.taskmanagementsystem.model.User;
import com.charniuk.taskmanagementsystem.service.AuthenticationService;
import com.charniuk.taskmanagementsystem.security.jwt.JwtService;
import com.charniuk.taskmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public JwtAuthenticationResponse signUp(SignUpRequest request) {

    var user = User.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .name(request.getName())
        .role(Role.ROLE_USER)
        .build();

    userService.create(user);

    var jwt = jwtService.generateToken(user);
    return new JwtAuthenticationResponse(jwt);
  }

  @Override
  public JwtAuthenticationResponse signIn(SignInRequest request) {

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword()
    ));

    var user = userService
        .getByEmail(request.getEmail());

    var jwt = jwtService.generateToken(user);
    return new JwtAuthenticationResponse(jwt);
  }
}
