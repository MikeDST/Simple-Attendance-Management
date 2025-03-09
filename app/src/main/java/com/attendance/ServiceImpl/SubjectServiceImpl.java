package com.attendance.ServiceImpl;

import com.attendance.Common.Message;
import com.attendance.DTO.SubjectDTO;
import com.attendance.Entity.Subject;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.Mapper.MapStructMapper;
import com.attendance.Repository.SubjectRepository;
import com.attendance.ServiceInterface.SubjectService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    private final Message message = new Message();

    @Override
    public void createSubject(SubjectDTO subjectDTO) {
        subjectDTO.setId(null);
        subjectRepository.saveAndFlush(MapStructMapper.MAPPER.toEntity(subjectDTO));
    }

    @SneakyThrows
    @Override
    public void updateSubject(UUID subjectId, SubjectDTO subjectDTO){
        Subject foundSubject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(message.SUBJECT_NOT_FOUND + subjectId));

        foundSubject.setName(subjectDTO.getName());
        subjectRepository.saveAndFlush(foundSubject);
    }

    @SneakyThrows
    @Override
    public void deleteSubject(UUID subjectId){
        Subject foundSubject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(message.SUBJECT_NOT_FOUND + subjectId));
        subjectRepository.delete(foundSubject);
    }

    @SneakyThrows
    @Override
    public SubjectDTO getSubject(UUID subjectId){
        Subject foundSubject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(message.SUBJECT_NOT_FOUND + subjectId));
        return MapStructMapper.MAPPER.toDto(foundSubject);
    }

    @SneakyThrows
    @Override
    public Collection<SubjectDTO> getSubjects(){
        Collection<Subject> subjects = subjectRepository.findAll();

        if(subjects.isEmpty()){
            throw new ResourceNotFoundException(message.NO_SUBJECT_FOUND);
        }

        return subjects.stream().map(MapStructMapper.MAPPER::toDto).collect(Collectors.toList());
    }
}
