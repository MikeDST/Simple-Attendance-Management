package com.attendance.ServiceImpl;

import com.attendance.Common.Message;
import com.attendance.DTO.SubjectDTO;
import com.attendance.Entity.Subject;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.Mapper.MapStructMapper;
import com.attendance.Repository.SubjectRepository;
import com.attendance.ServiceInterface.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    private MapStructMapper mapStructMapper;
    private final Message message = new Message();

    @Override
    public void createSubject(SubjectDTO subjectDTO) {
        subjectDTO.setId(null);
        subjectRepository.saveAndFlush(mapStructMapper.MAPPER.toEntity(subjectDTO));
    }

    @Override
    public void updateSubject(UUID subjectId, SubjectDTO subjectDTO) throws ResourceNotFoundException {
        Subject foundSubject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(message.SUBJECT_NOT_FOUND + subjectId));

        // Subject subject = mapStructMapper.MAPPER.toEntity(subjectDTO);
        foundSubject.setName(subjectDTO.getName());
        subjectRepository.saveAndFlush(foundSubject);
    }

    @Override
    public void deleteSubject(UUID subjectId) throws ResourceNotFoundException {
        Subject foundSubject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(message.SUBJECT_NOT_FOUND + subjectId));
        subjectRepository.delete(foundSubject);
    }

    @Override
    public SubjectDTO getSubject(UUID subjectId) throws ResourceNotFoundException {
        Subject foundSubject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(message.SUBJECT_NOT_FOUND + subjectId));
        return mapStructMapper.MAPPER.toDto(foundSubject);
    }

    @Override
    public Collection<SubjectDTO> getSubjects() throws ResourceNotFoundException {
        Collection<Subject> subjects = subjectRepository.findAll();

        if(subjects.isEmpty()){
            throw new ResourceNotFoundException(message.NO_SUBJECT_FOUND);
        }

        return subjects.stream().map(mapStructMapper.MAPPER::toDto).collect(Collectors.toList());
    }
}
