package com.attendance.ServiceInterface;

import com.attendance.DTO.SubjectDTO;
import com.attendance.Exception.ResourceNotFoundException;

import java.util.Collection;
import java.util.UUID;

public interface SubjectService {
    void createSubject(SubjectDTO subjectDTO);
    void updateSubject(UUID subjectId, SubjectDTO subjectDTO);
    void deleteSubject(UUID subjectId);
    SubjectDTO getSubject(UUID subjectId);
    Collection<SubjectDTO> getSubjects();
}
