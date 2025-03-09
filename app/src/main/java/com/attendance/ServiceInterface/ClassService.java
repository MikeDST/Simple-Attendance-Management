package com.attendance.ServiceInterface;

import com.attendance.DTO.ClassDTO;
import com.attendance.DTO.ClassEditDTO;
import com.attendance.Exception.ResourceNotFoundException;

import java.util.Collection;
import java.util.UUID;

public interface ClassService {
    void createClass(ClassEditDTO classEditDTO);
    void updateClass(UUID classId, ClassEditDTO classEditDTO);
    void deleteClass(UUID classId);
    ClassDTO getClass(UUID classId);
    Collection<ClassDTO> getMyClasses(UUID myId);
    Collection<ClassDTO> getClasses();
}