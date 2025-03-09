package com.attendance.ServiceInterface;

import com.attendance.DTO.ClassDTO;
import com.attendance.DTO.ClassEditDTO;
import com.attendance.Exception.ResourceNotFoundException;

import java.util.Collection;
import java.util.UUID;

public interface ClassService {
    void createClass(ClassEditDTO classEditDTO) throws ResourceNotFoundException;
    void updateClass(UUID classId, ClassEditDTO classEditDTO) throws ResourceNotFoundException;
    void deleteClass(UUID classId) throws ResourceNotFoundException;
    ClassDTO getClass(UUID classId) throws ResourceNotFoundException;
    Collection<ClassDTO> getMyClasses(UUID myId) throws ResourceNotFoundException;
    Collection<ClassDTO> getClasses() throws ResourceNotFoundException;
}