package com.charniuk.taskmanagementsystem.controller.impl;

import com.charniuk.taskmanagementsystem.controller.AuthController;
import com.charniuk.taskmanagementsystem.dto.request.SignInRequest;
import com.charniuk.taskmanagementsystem.dto.request.SignUpRequest;
import com.charniuk.taskmanagementsystem.dto.response.JwtAuthenticationResponse;
import com.charniuk.taskmanagementsystem.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

  private final AuthenticationService authenticationService;

  @Override
  @PostMapping("/sign-up")
  public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
    return authenticationService.signUp(request);
  }

  @Override
  @PostMapping("/sign-in")
  public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
    return authenticationService.signIn(request);
  }
}
