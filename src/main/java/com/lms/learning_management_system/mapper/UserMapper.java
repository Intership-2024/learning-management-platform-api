package com.lms.learning_management_system.mapper;

import com.lms.learning_management_system.dto.UserDTO;
import com.lms.learning_management_system.entities.RoleEntity;
import com.lms.learning_management_system.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "role.id", target = "roleId")
    UserDTO toDTO(UserEntity user);

    @Mapping(source = "roleId", target = "role", qualifiedByName = "idToRole")
    UserEntity toEntity(UserDTO userDTO);

    @Named("idToRole")
    default RoleEntity idToRole(UUID id) {
        if (id == null) {
            return null;
        }
        RoleEntity role = new RoleEntity();
        role.setId(id);
        return role;
    }
}
