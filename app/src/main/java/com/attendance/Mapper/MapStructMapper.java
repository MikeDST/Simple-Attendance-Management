package com.attendance.Mapper;

import com.attendance.DTO.BeanDTO;
import com.attendance.Entity.Bean;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructMapper {
    MapStructMapper MAPPER = Mappers.getMapper(MapStructMapper.class);
    BeanDTO toDto(Bean bean);

    Bean toEntity(BeanDTO beanDTO);
}
