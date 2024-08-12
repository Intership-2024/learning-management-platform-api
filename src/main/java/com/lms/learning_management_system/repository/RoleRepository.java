package com.lms.learning_management_system.repository;

import com.lms.learning_management_system.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
}
