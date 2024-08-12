package com.lms.learning_management_system.entityTest;

import com.lms.learning_management_system.entities.*;
import com.lms.learning_management_system.repository.CourseRepository;
import com.lms.learning_management_system.repository.ModuleRepository;
import com.lms.learning_management_system.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourseEntityTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Test
    @Transactional
    void testCourseEntityBasicProperties() {
        CourseEntity course = new CourseEntity();
        course.setTitle("Course Title");
        course.setDescription("Course Description");

        CourseEntity savedCourse = courseRepository.save(course);

        assertNotNull(savedCourse.getId());
        assertEquals("Course Title", savedCourse.getTitle());
        assertEquals("Course Description", savedCourse.getDescription());
    }

    @Test
    @Transactional
    void testCourseEntityRelationships() {
        UserEntity teacher = new UserEntity();
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        teacher.setEmail("john.doe@example.com");
        RoleEntity teacherRoleEntity = new RoleEntity();
        teacherRoleEntity.setRole(RoleEnum.TEACHER);
        teacher.setRole(teacherRoleEntity);
        userRepository.save(teacher);

        // Create and save students
        UserEntity student1 = new UserEntity();
        student1.setFirstName("Jane");
        student1.setLastName("Smith");
        student1.setEmail("jane.smith@example.com");
        RoleEntity studentRoleEntity = new RoleEntity();
        studentRoleEntity.setRole(RoleEnum.STUDENT);
        userRepository.save(student1);

        UserEntity student2 = new UserEntity();
        student2.setFirstName("Emily");
        student2.setLastName("Jones");
        student2.setEmail("emily.jones@example.com");
        student2.setRole(studentRoleEntity);
        userRepository.save(student2);

        // Create and save modules
        ModuleEntity module1 = new ModuleEntity();
        module1.setTitle("Module 1");
        module1.setDescription("Description for Module 1");
        moduleRepository.save(module1);

        ModuleEntity module2 = new ModuleEntity();
        module2.setTitle("Module 2");
        module2.setDescription("Description for Module 2");
        moduleRepository.save(module2);

        // Create and save a course with relationships
        CourseEntity course = new CourseEntity();
        course.setTitle("Course with Relationships");
        course.setDescription("Course Description");
        course.setTeacher(teacher);
        course.setStudents(Arrays.asList(student1, student2));
        course.setModules(Arrays.asList(module1, module2));

        CourseEntity savedCourse = courseRepository.save(course);

        assertNotNull(savedCourse.getId());
        assertEquals("Course with Relationships", savedCourse.getTitle());
        assertEquals("Course Description", savedCourse.getDescription());
        assertEquals(teacher.getId(), savedCourse.getTeacher().getId());
        assertTrue(savedCourse.getStudents().stream().anyMatch(student -> student.getId().equals(student1.getId())));
        assertTrue(savedCourse.getStudents().stream().anyMatch(student -> student.getId().equals(student2.getId())));
        assertTrue(savedCourse.getModules().stream().anyMatch(module -> module.getId().equals(module1.getId())));
        assertTrue(savedCourse.getModules().stream().anyMatch(module -> module.getId().equals(module2.getId())));
    }
}