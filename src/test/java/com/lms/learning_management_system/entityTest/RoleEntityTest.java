package com.lms.learning_management_system.entityTest;

import com.lms.learning_management_system.entities.RoleEntity;
import com.lms.learning_management_system.entities.RoleEnum;
import com.lms.learning_management_system.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleEntityTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Transactional
    void testRoleEntityNoArgsConstructor() {
        RoleEntity roleEntity = new RoleEntity();

        assertNull(roleEntity.getId(), "ID should be null before saving");
        assertNull(roleEntity.getRole(), "Role should be null before saving");
    }

    @Test
    @Transactional
    void testRoleEntitySaveGeneratesId() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(RoleEnum.STUDENT);

        RoleEntity savedRoleEntity = roleRepository.save(roleEntity);

        assertNotNull(savedRoleEntity.getId(), "ID should be generated and not null");
        assertTrue(savedRoleEntity.getId().version() > 0, "UUID should be a valid UUID");
        assertEquals(RoleEnum.STUDENT, savedRoleEntity.getRole(), "Role should be STUDENT");
    }

    @Test
    @Transactional
    void testRoleEntityRoleSetterGetter() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(RoleEnum.TEACHER);

        assertEquals(RoleEnum.TEACHER, roleEntity.getRole(), "Role should be set correctly");
    }
}
