package com.lms.learning_management_system.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;

    @ManyToOne
    private UserEntity teacher;

    @ManyToMany
    private List<UserEntity> students;

    @OneToMany(mappedBy = "course")
    private List<ModuleEntity> modules;
}
