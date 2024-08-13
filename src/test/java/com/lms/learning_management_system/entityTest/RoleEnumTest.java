package com.lms.learning_management_system.entityTest;

import com.lms.learning_management_system.entities.RoleEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoleEnumTest {

    @Test
    void testRoleEnumFromString() {
        assertEquals(RoleEnum.ADMIN, RoleEnum.fromString("ADMIN"));
        assertEquals(RoleEnum.STUDENT, RoleEnum.fromString("STUDENT"));
        assertEquals(RoleEnum.TEACHER, RoleEnum.fromString("TEACHER"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RoleEnum.fromString("INVALID_ROLE");
        });

        assertEquals("No enum constant for role: INVALID_ROLE", exception.getMessage());
    }
}
