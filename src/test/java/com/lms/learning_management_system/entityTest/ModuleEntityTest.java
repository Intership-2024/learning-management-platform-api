package com.lms.learning_management_system.entityTest;

import com.lms.learning_management_system.entities.ModuleEntity;
import com.lms.learning_management_system.repository.ModuleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ModuleEntityTest {

    @Autowired
    private ModuleRepository moduleRepository; // Assuming you have a repository for ModuleEntity

    @Test
    @Transactional
    void testModuleEntity() {
        ModuleEntity module = new ModuleEntity();
        module.setTitle("Module Title");
        module.setDescription("Module Description");

        ModuleEntity savedModule = moduleRepository.save(module);

        assertNotNull(savedModule.getId());
        assertEquals("Module Title", savedModule.getTitle());
        assertEquals("Module Description", savedModule.getDescription());
    }
}
