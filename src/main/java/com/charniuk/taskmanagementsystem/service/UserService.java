package com.charniuk.taskmanagementsystem.service;

import com.charniuk.taskmanagementsystem.model.User;
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
   * Получение пользователя по имени пользователя
   *
   * @return пользователь
   */
  UserDetailsService userDetailsService();

  /**
   * Получение текущего пользователя
   *
   * @return текущий пользователь
   */
  User getCurrentUser();

  /**
   * Выдача прав администратора текущему пользователю
   */
  void getAdmin();
}
