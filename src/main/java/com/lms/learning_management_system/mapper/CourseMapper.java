package com.lms.learning_management_system.mapper;

import com.lms.learning_management_system.dto.CourseDTO;
import com.lms.learning_management_system.entities.CourseEntity;
import com.lms.learning_management_system.entities.ModuleEntity;
import com.lms.learning_management_system.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "students", target = "studentIds", qualifiedByName = "studentsToStudentIds")
    @Mapping(source = "modules", target = "moduleIds", qualifiedByName = "modulesToModuleIds")
    CourseDTO toDTO(CourseEntity course);

    @Mapping(source = "teacherId", target = "teacher", qualifiedByName = "idToUser")
    @Mapping(source = "studentIds", target = "students", qualifiedByName = "studentIdsToStudents")
    @Mapping(source = "moduleIds", target = "modules", qualifiedByName = "moduleIdsToModules")
    CourseEntity toEntity(CourseDTO courseDTO);

    @Named("studentsToStudentIds")
    default List<UUID> studentsToStudentIds(List<UserEntity> students) {
        return students == null ? null : students.stream().map(UserEntity::getId).collect(Collectors.toList());
    }

    @Named("studentIdsToStudents")
    default List<UserEntity> studentIdsToStudents(List<UUID> studentIds) {
        return studentIds == null ? null : studentIds.stream().map(id -> {
            UserEntity user = new UserEntity();
            user.setId(id);
            return user;
        }).collect(Collectors.toList());
    }

    @Named("modulesToModuleIds")
    default List<UUID> modulesToModuleIds(List<ModuleEntity> modules) {
        return modules == null ? null : modules.stream().map(ModuleEntity::getId).collect(Collectors.toList());
    }

    @Named("moduleIdsToModules")
    default List<ModuleEntity> moduleIdsToModules(List<UUID> moduleIds) {
        return moduleIds == null ? null : moduleIds.stream().map(id -> {
            ModuleEntity module = new ModuleEntity();
            module.setId(id);
            return module;
        }).collect(Collectors.toList());
    }

    @Named("idToUser")
    default UserEntity idToUser(UUID id) {
        if (id == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }
}