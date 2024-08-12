package com.lms.learning_management_system;

import com.lms.learning_management_system.dto.UserDTO;
import com.lms.learning_management_system.entities.RoleEntity;
import com.lms.learning_management_system.entities.RoleEnum;
import com.lms.learning_management_system.entities.UserEntity;
import com.lms.learning_management_system.exception.UserAlreadyExistsException;
import com.lms.learning_management_system.exception.UserNotFoundException;
import com.lms.learning_management_system.repository.UserRepository;
import com.lms.learning_management_system.entities.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserEntity userEntity;
    private UserDTO userDTO;
    private UUID userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(RoleEnum.STUDENT);

        userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");
        userEntity.setEmail("john.doe@example.com");
        userEntity.setRole(roleEntity);

        userDTO = new UserDTO(userId, "John", "Doe", "john.doe@example.com", RoleEnum.STUDENT.toString());
    }

    @Test
    void createUser_ShouldCreateUser_WhenUserDoesNotExist() {
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserDTO createdUser = userService.createUser(userDTO);

        assertNotNull(createdUser);
        assertEquals(userDTO.getEmail(), createdUser.getEmail());
        assertEquals(userDTO.getRole(), createdUser.getRole());
        verify(userRepository).findByEmail(userDTO.getEmail());
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    void createUser_ShouldThrowException_WhenUserAlreadyExists() {
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(userEntity));

        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(userDTO));
        verify(userRepository).findByEmail(userDTO.getEmail());
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void getUserById_ShouldReturnUser_WhenUserExists() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        UserDTO foundUser = userService.getUserById(userId);

        assertNotNull(foundUser);
        assertEquals(userDTO.getEmail(), foundUser.getEmail());
        assertEquals(userDTO.getRole(), foundUser.getRole());
        verify(userRepository).findById(userId);
    }

    @Test
    void getUserById_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
        verify(userRepository).findById(userId);
    }

    @Test
    void getAllUsers_ShouldReturnAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(userEntity));

        List<UserDTO> users = userService.getAllUsers();

        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        verify(userRepository).findAll();
    }

    @Test
    void updateUser_ShouldUpdateUser_WhenUserExists() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserDTO updatedUser = userService.updateUser(userId, userDTO);

        assertNotNull(updatedUser);
        assertEquals(userDTO.getEmail(), updatedUser.getEmail());
        assertEquals(userDTO.getRole(), updatedUser.getRole());
        verify(userRepository).findById(userId);
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    void updateUser_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userId, userDTO));
        verify(userRepository).findById(userId);
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void deleteUser_ShouldDeleteUser_WhenUserExists() {
        when(userRepository.existsById(userId)).thenReturn(true);
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository).existsById(userId);
        verify(userRepository).deleteById(userId);
    }

    @Test
    void deleteUser_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
        verify(userRepository).existsById(userId);
        verify(userRepository, never()).deleteById(userId);
    }
}
