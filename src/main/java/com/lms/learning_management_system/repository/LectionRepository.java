package com.lms.learning_management_system.repository;

import com.lms.learning_management_system.entities.LectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LectionRepository extends JpaRepository<LectionEntity, UUID> {
}
