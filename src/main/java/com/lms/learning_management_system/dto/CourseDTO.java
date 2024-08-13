package com.lms.learning_management_system.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private UUID id;
    private String title;
    private String description;
    private UUID teacherId;
    private List<UUID> studentIds;

}

