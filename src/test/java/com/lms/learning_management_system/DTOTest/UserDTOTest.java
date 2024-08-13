package com.lms.learning_management_system.DTOTest;

import com.lms.learning_management_system.dto.UserDTO;
import com.lms.learning_management_system.entities.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    private UserDTO userDTO;
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        firstName = "John";
        lastName = "Doe";
        email = "john.doe@example.com";
        role = "STUDENT";
        userDTO = new UserDTO(id, firstName, lastName, email, role);
    }

    @Test
    void testGetters() {
        assertEquals(id, userDTO.getId());
        assertEquals(firstName, userDTO.getFirstName());
        assertEquals(lastName, userDTO.getLastName());
        assertEquals(email, userDTO.getEmail());
        assertEquals(RoleEnum.STUDENT, userDTO.getRole());
    }

    @Test
    void testSetters() {
        UUID newId = UUID.randomUUID();
        String newFirstName = "Jane";
        String newLastName = "Smith";
        String newEmail = "jane.smith@example.com";
        String newRole = "TEACHER";

        userDTO.setId(newId);
        userDTO.setFirstName(newFirstName);
        userDTO.setLastName(newLastName);
        userDTO.setEmail(newEmail);
        userDTO.setRole(newRole);

        assertEquals(newId, userDTO.getId());
        assertEquals(newFirstName, userDTO.getFirstName());
        assertEquals(newLastName, userDTO.getLastName());
        assertEquals(newEmail, userDTO.getEmail());
        assertEquals(RoleEnum.TEACHER, userDTO.getRole());
    }

    @Test
    void testDefaultConstructor() {
        UserDTO defaultUserDTO = new UserDTO();
        assertNull(defaultUserDTO.getId());
        assertNull(defaultUserDTO.getFirstName());
        assertNull(defaultUserDTO.getLastName());
        assertNull(defaultUserDTO.getEmail());
        assertNull(defaultUserDTO.getRole());
    }

    @Test
    void testInvalidRole() {
        // First, set an invalid role to trigger the exception
        userDTO.setRole("INVALID_ROLE");

        // Then, verify that getting the role results in an exception
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            userDTO.getRole();
        });

        String expectedMessage = "No enum constant for role: INVALID_ROLE";
        String actualMessage = thrown.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
