package com.attendance.Mapper;

import com.attendance.DTO.BeanDTO;
import com.attendance.DTO.UserRegisterDTO;
import com.attendance.Entity.AppUser;
import com.attendance.Entity.Bean;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructMapper {
    MapStructMapper MAPPER = Mappers.getMapper(MapStructMapper.class);
    // Entity to DTO
    BeanDTO toDto(Bean bean);

    // DTO to Entity
    Bean toEntity(BeanDTO beanDTO);
    AppUser toEntity(UserRegisterDTO userRegisterDTO);
}
