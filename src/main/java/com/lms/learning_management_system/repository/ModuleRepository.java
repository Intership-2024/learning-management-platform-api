package com.lms.learning_management_system.repository;

import com.lms.learning_management_system.entities.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleEntity, UUID> {
}
