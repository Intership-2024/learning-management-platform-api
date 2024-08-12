package com.lms.learning_management_system.mapper;

import com.lms.learning_management_system.dto.ModuleDTO;
import com.lms.learning_management_system.entities.ModuleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModuleMapper {
    ModuleMapper INSTANCE = Mappers.getMapper(ModuleMapper.class);

    ModuleDTO toDTO(ModuleEntity module);

    ModuleEntity toEntity(ModuleDTO moduleDTO);
}

