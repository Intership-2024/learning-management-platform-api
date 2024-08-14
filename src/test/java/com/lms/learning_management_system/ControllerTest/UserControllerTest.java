package com.lms.learning_management_system.ControllerTest;

import com.lms.learning_management_system.controller.UserController;
import com.lms.learning_management_system.config.SecurityTestConfig;
import com.lms.learning_management_system.dto.UserDTO;
import com.lms.learning_management_system.entities.RoleEnum;
import com.lms.learning_management_system.entities.service.UserService;
import com.lms.learning_management_system.exception.UserNotFoundException;
import com.lms.learning_management_system.models.UserRequest;
import com.lms.learning_management_system.models.UserResponse;
import com.lms.learning_management_system.security.UnAuthorizedUserAuthenticationEntryPoint;
import com.lms.learning_management_system.utils.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityTestConfig.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UnAuthorizedUserAuthenticationEntryPoint unauthorizedUserAuthenticationEntryPoint;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private JWTUtil jwtUtil;

    @Test
    void testCreateUser() throws Exception {
        UserDTO userDTO = new UserDTO(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", RoleEnum.STUDENT.toString());
        when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"role\":\"STUDENT\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.role").value("STUDENT"));
    }

    @Test
    void testLogin() throws Exception {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mock(Authentication.class));
        when(jwtUtil.generateToken(any(String.class))).thenReturn("mockedToken");

        mockMvc.perform(post("/users/loginUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"john.doe\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Token generated successfully!"))
                .andExpect(jsonPath("$.token").value("mockedToken"));
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