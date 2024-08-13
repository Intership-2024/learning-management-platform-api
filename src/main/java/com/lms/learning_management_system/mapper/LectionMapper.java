package com.lms.learning_management_system.mapper;

import com.lms.learning_management_system.dto.LectionDTO;
import com.lms.learning_management_system.entities.LectionEntity;
import com.lms.learning_management_system.entities.ModuleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface LectionMapper {
    LectionMapper INSTANCE = Mappers.getMapper(LectionMapper.class);

    @Mapping(source = "module.id", target = "moduleId")
    LectionDTO toDTO(LectionEntity lection);

    @Mapping(source = "moduleId", target = "module", qualifiedByName = "idToModule")
    LectionEntity toEntity(LectionDTO lectionDTO);

    @Named("idToModule")
    default ModuleEntity idToModule(UUID moduleId) {
        if (moduleId == null) {
            return null;
        }
        ModuleEntity module = new ModuleEntity();
        module.setId(moduleId);
        return module;
    }
}
