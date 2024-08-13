package com.lms.learning_management_system.DTOTest;

import com.lms.learning_management_system.dto.CourseDTO;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class CourseDTOTest {

    @Test
    void testCourseDTO() {
        UUID id = UUID.randomUUID();
        String title = "Course Title";
        String description = "Course Description";
        UUID teacherId = UUID.randomUUID();
        List<UUID> studentIds = List.of(UUID.randomUUID(), UUID.randomUUID());

        CourseDTO courseDTO = new CourseDTO(id, title, description, teacherId, studentIds);

        assertEquals(id, courseDTO.getId());
        assertEquals(title, courseDTO.getTitle());
        assertEquals(description, courseDTO.getDescription());
        assertEquals(teacherId, courseDTO.getTeacherId());
        assertEquals(studentIds, courseDTO.getStudentIds());
    }
}
