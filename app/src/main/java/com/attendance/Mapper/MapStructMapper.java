package com.attendance.Mapper;

import com.attendance.DTO.*;
import com.attendance.Entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructMapper {
    MapStructMapper MAPPER = Mappers.getMapper(MapStructMapper.class);
    // Entity to DTO
    BeanDTO toDto(Bean bean);
    AttendanceDTO toDto(Attendance attendance);

    // DTO to Entity
    Bean toEntity(BeanDTO beanDTO);
    AppUser toEntity(RegisterDTO registerDTO);
    Attendance toEntity(AttendanceDTO attendanceDTO);
}
