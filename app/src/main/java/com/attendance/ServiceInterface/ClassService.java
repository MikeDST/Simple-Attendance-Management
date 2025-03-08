package com.attendance.ServiceInterface;

import com.attendance.DTO.ClassDTO;
import com.attendance.Exception.ResourceNotFoundException;

import java.util.Collection;
import java.util.UUID;

public interface ClassService {
    void createClass(ClassDTO classDTO);
    void updateClass(UUID classId, ClassDTO classDTO) throws ResourceNotFoundException;
    void deleteClass(UUID classId) throws ResourceNotFoundException;
    ClassDTO getClass(UUID classId) throws ResourceNotFoundException;
    Collection<ClassDTO> getClasses() throws ResourceNotFoundException;
}