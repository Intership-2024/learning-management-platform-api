package com.lms.learning_management_system.entities.service;

import com.lms.learning_management_system.entities.UserEntity;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    UUID saveUser(UserEntity user);

    Optional<UserEntity> findByUsername(String username);
}