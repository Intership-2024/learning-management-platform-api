package com.lms.learning_management_system.ControllerTest;

import com.lms.learning_management_system.controller.UserController;
import com.lms.learning_management_system.dto.UserDTO;
import com.lms.learning_management_system.entities.RoleEnum;
import com.lms.learning_management_system.entities.service.UserService;
import com.lms.learning_management_system.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testCreateUser() throws Exception {
        UserDTO userDTO = new UserDTO(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", RoleEnum.STUDENT.toString());
        when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"role\":\"STUDENT\"}"))
                .andExpect(status().isCreated())  // Check for HTTP 201 Created status
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.role").value("STUDENT"));
    }

    @Test
    void testUpdateUser() throws Exception {
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = new UserDTO(userId, "John", "Doe", "john.doe@example.com", RoleEnum.STUDENT.toString());
        when(userService.updateUser(eq(userId), any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(put("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"role\":\"STUDENT\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.role").value("STUDENT"));
    }

    @Test
    void testGetUser() throws Exception {
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = new UserDTO(userId, "John", "Doe", "john.doe@example.com", RoleEnum.STUDENT.toString());
        when(userService.getUserById(userId)).thenReturn(userDTO);

        mockMvc.perform(get("/users/{id}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.role").value("STUDENT"));
    }

    @Test
    void testGetUserNotFound() throws Exception {
        UUID userId = UUID.randomUUID();
        when(userService.getUserById(userId)).thenThrow(new UserNotFoundException("User with Id " + userId + " Not found"));

        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateUserNotFound() throws Exception {
        UUID userId = UUID.randomUUID();
        when(userService.updateUser(eq(userId), any(UserDTO.class))).thenThrow(new UserNotFoundException("User with Id " + userId + " Not found"));

        mockMvc.perform(put("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"role\":\"STUDENT\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteUser() throws Exception {
        UUID userId = UUID.randomUUID();
        doNothing().when(userService).deleteUser(userId);

        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteUserNotFound() throws Exception {
        UUID userId = UUID.randomUUID();
        doThrow(new UserNotFoundException("User with Id " + userId + " Not found")).when(userService).deleteUser(userId);

        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isNotFound());
    }
}
