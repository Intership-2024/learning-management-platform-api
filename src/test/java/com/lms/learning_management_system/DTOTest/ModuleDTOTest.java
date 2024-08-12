package com.lms.learning_management_system.DTOTest;

import com.lms.learning_management_system.dto.ModuleDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ModuleDTOTest {

    @Test
    void testNoArgsConstructor() {
        ModuleDTO moduleDTO = new ModuleDTO();

        // Verify default values of properties
        assertThat(moduleDTO.getId()).isNull();
        assertThat(moduleDTO.getTitle()).isNull();
        assertThat(moduleDTO.getDescription()).isNull();
    }

    void testAllArgsConstructor() {
        // Create a list of UUIDs for lectionIds
        List<UUID> lectionIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());

        // Use the all-args constructor to create a ModuleDTO
        ModuleDTO moduleDTO = new ModuleDTO(
                UUID.randomUUID(),
                "Module Title",
                "Module Description",
                lectionIds
        );

        // Verify the values set by the all-args constructor
        assertThat(moduleDTO.getId()).isNotNull();
        assertThat(moduleDTO.getTitle()).isEqualTo("Module Title");
        assertThat(moduleDTO.getDescription()).isEqualTo("Module Description");
        assertThat(moduleDTO.getLectionIds()).isEqualTo(lectionIds);
    }

    @Test
    void testSettersAndGetters() {
        // Create a ModuleDTO instance using the no-args constructor
        ModuleDTO moduleDTO = new ModuleDTO();

        // Set values using setters
        UUID uuid = UUID.randomUUID();
        moduleDTO.setId(uuid);
        moduleDTO.setTitle("Updated Title");
        moduleDTO.setDescription("Updated Description");

        // Verify values using getters
        assertThat(moduleDTO.getId()).isEqualTo(uuid);
        assertThat(moduleDTO.getTitle()).isEqualTo("Updated Title");
        assertThat(moduleDTO.getDescription()).isEqualTo("Updated Description");
    }
}