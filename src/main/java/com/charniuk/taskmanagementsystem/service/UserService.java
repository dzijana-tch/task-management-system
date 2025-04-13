package com.charniuk.taskmanagementsystem.service;

import com.charniuk.taskmanagementsystem.model.User;
import java.util.UUID;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

  /**
   * Сохранение пользователя
   *
   * @return сохраненный пользователь
   */
  User save(User user);

  /**
   * Создание пользователя
   *
   * @return созданный пользователь
   */
  User create(User user);

  /**
   * Получение пользователя по электронной почте
   *
   * @return пользователь
   */
  User getByEmail(String email);

  /**
   * Получение пользователя по айди
   *
   * @return пользователь
   */
  User getByUserId(UUID userId);

  /**
   * Получение пользователя по имени пользователя
   *
   * @return пользователь
   */
  UserDetailsService userDetailsService();
}
