package com.lms.learning_management_system.DTOTest;

import com.lms.learning_management_system.dto.LectionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LectionDTOTest {

    private LectionDTO lectionDTO;
    private UUID id;
    private String title;
    private String description;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        title = "Sample Title";
        description = "Sample Description";
        lectionDTO = new LectionDTO(id, title, description);
    }

    @Test
    void testGetters() {
        assertEquals(id, lectionDTO.getId());
        assertEquals(title, lectionDTO.getTitle());
        assertEquals(description, lectionDTO.getDescription());
    }

    @Test
    void testSetters() {
        UUID newId = UUID.randomUUID();
        String newTitle = "New Title";
        String newDescription = "New Description";

        lectionDTO.setId(newId);
        lectionDTO.setTitle(newTitle);
        lectionDTO.setDescription(newDescription);

        assertEquals(newId, lectionDTO.getId());
        assertEquals(newTitle, lectionDTO.getTitle());
        assertEquals(newDescription, lectionDTO.getDescription());
    }

    @Test
    void testNoArgsConstructor() {
        LectionDTO defaultLectionDTO = new LectionDTO();
        assertNull(defaultLectionDTO.getId());
        assertNull(defaultLectionDTO.getTitle());
        assertNull(defaultLectionDTO.getDescription());
    }
}
