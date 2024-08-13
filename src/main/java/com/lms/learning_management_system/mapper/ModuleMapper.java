package com.lms.learning_management_system.mapper;

import com.lms.learning_management_system.dto.ModuleDTO;
import com.lms.learning_management_system.entities.ModuleEntity;
import com.lms.learning_management_system.entities.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface ModuleMapper {
    ModuleMapper INSTANCE = Mappers.getMapper(ModuleMapper.class);

    @Mapping(source = "course.id", target = "courseId")
    ModuleDTO toDTO(ModuleEntity module);

    @Mapping(source = "courseId", target = "course", qualifiedByName = "idToCourse")
    ModuleEntity toEntity(ModuleDTO moduleDTO);

    @Named("idToCourse")
    default CourseEntity idToCourse(UUID id) {
        if (id == null) {
            return null;
        }
        CourseEntity course = new CourseEntity();
        course.setId(id);
        return course;
    }
}
