package com.attendance.Mapper;

import com.attendance.DTO.*;
import com.attendance.Entity.*;
import com.attendance.Entity.Class;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructMapper {
    MapStructMapper MAPPER = Mappers.getMapper(MapStructMapper.class);
    // Entity to DTO
    BeanDTO toDto(Bean bean);
    UserDTO toDto(AppUser appUser);
    ClassDTO toDto(Class aClass);
    SubjectDTO toDto(Subject subject);
    AttendanceDTO toDto(Attendance attendance);

    // DTO to Entity
    Bean toEntity(BeanDTO beanDTO);
    AppUser toEntity(RegisterDTO registerDTO);
    Class toEntity(ClassDTO classDTO);
    Subject toEntity(SubjectDTO subjectDTO);
    Attendance toEntity(AttendanceDTO attendanceDTO);
}
