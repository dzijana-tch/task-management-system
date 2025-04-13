package com.charniuk.taskmanagementsystem.service.impl;

import com.charniuk.taskmanagementsystem.exceptions.NotFoundException;
import com.charniuk.taskmanagementsystem.exceptions.UserAlreadyExistsException;
import com.charniuk.taskmanagementsystem.model.User;
import com.charniuk.taskmanagementsystem.repository.UserRepository;
import com.charniuk.taskmanagementsystem.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Override
  @Transactional
  public User save(User user) {
    return repository.save(user);
  }

  @Override
  @Transactional
  public User create(User user) {
    if (repository.existsByEmail(user.getEmail())) {
      throw new UserAlreadyExistsException("Пользователь с email " + user.getEmail() + " уже существует");
    }
    return save(user);
  }

  @Override
  public User getByEmail(String email) {
    return repository.findByEmail(email)
        .orElseThrow(() -> new NotFoundException("Пользователь " + email + " не найден"));
  }

  @Override
  public User getByUserId(UUID userId) {
    return repository.findById(userId)
        .orElseThrow(() -> new NotFoundException("Пользователь " + userId + " не найден"));
  }

  @Override
  public UserDetailsService userDetailsService() {
    return this::getByEmail;
  }
}
