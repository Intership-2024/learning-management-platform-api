package com.lms.learning_management_system.DTO;



import com.lms.learning_management_system.entities.RoleEnum;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;


    public UserDTO() {}

    public UserDTO(UUID id, String firstName, String lastName, String email, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleEnum getRole() {
        return RoleEnum.fromString(role);
    }

    public void setRole(String role) {
        this.role = role;
    }
}

