package com.charniuk.taskmanagementsystem.service;

import com.charniuk.taskmanagementsystem.dto.request.SignInRequest;
import com.charniuk.taskmanagementsystem.dto.request.SignUpRequest;
import com.charniuk.taskmanagementsystem.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {

  /**
   * Регистрация пользователя
   *
   * @param request данные пользователя
   * @return токен
   */
  JwtAuthenticationResponse signUp(SignUpRequest request);

  /**
   * Аутентификация пользователя
   *
   * @param request данные пользователя
   * @return токен
   */
  JwtAuthenticationResponse signIn(SignInRequest request);
}
