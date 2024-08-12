package com.lms.learning_management_system.dto;

import com.lms.learning_management_system.entities.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    public RoleEnum getRole() {
        if (role == null) {
            return null; // or handle null value as needed
        }
        return RoleEnum.fromString(role);
    }

    public void setRole(String role) {
        this.role = role;
    }
}