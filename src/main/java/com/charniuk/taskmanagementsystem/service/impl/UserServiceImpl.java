package com.charniuk.taskmanagementsystem.service.impl;

import com.charniuk.taskmanagementsystem.enums.Role;
import com.charniuk.taskmanagementsystem.exceptions.UserAlreadyExistsException;
import com.charniuk.taskmanagementsystem.model.User;
import com.charniuk.taskmanagementsystem.repository.UserRepository;
import com.charniuk.taskmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Override
  public User save(User user) {
    return repository.save(user);
  }

  @Override
  public User create(User user) {
    if (repository.existsByEmail(user.getEmail())) {
      throw new UserAlreadyExistsException("Пользователь с email " + user.getEmail() + " уже существует");
    }
    return save(user);
  }

  @Override
  public User getByEmail(String email) {
    return repository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
  }

  @Override
  public UserDetailsService userDetailsService() {
    return this::getByEmail;
  }

  @Override
  public User getCurrentUser() {
    var mail = SecurityContextHolder.getContext().getAuthentication().getName();
    return getByEmail(mail);
  }

  @Override
  public void getAdmin() {
    var user = getCurrentUser();
    user.setRole(Role.ROLE_ADMIN);
    save(user);
  }
}
