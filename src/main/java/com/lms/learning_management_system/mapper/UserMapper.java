package com.lms.learning_management_system.mapper;


import com.lms.learning_management_system.dto.UserDTO;
import com.lms.learning_management_system.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(UserEntity user);

    UserEntity toEntity(UserDTO userDTO);
}

