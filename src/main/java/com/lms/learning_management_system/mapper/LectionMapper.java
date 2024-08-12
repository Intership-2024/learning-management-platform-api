package com.lms.learning_management_system.mapper;

import com.lms.learning_management_system.dto.LectionDTO;
import com.lms.learning_management_system.entities.LectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LectionMapper {
    LectionMapper INSTANCE = Mappers.getMapper(LectionMapper.class);

    LectionDTO toDTO(LectionEntity lection);

    LectionEntity toEntity(LectionDTO lectionDTO);
}

