package com.lms.learning_management_system.entityTest;

import com.lms.learning_management_system.entities.LectionEntity;
import com.lms.learning_management_system.repository.LectionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LectionEntityTest {

    @Autowired
    private LectionRepository lectionRepository;

    @Test
    @Transactional
    void testLectionEntity() {
        LectionEntity lection = new LectionEntity();
        lection.setTitle("Lection Title");
        lection.setDescription("Lection Description");

        LectionEntity savedLection = lectionRepository.save(lection);

        assertNotNull(savedLection.getId());
        assertEquals("Lection Title", savedLection.getTitle());
        assertEquals("Lection Description", savedLection.getDescription());
    }
}
