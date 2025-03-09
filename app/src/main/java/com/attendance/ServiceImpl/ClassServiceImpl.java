package com.attendance.ServiceImpl;

import com.attendance.Common.Message;
import com.attendance.DTO.ClassDTO;
import com.attendance.DTO.ClassEditDTO;
import com.attendance.Entity.AppUser;
import com.attendance.Entity.Class;
import com.attendance.Entity.Subject;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.Mapper.MapStructMapper;
import com.attendance.Repository.ClassRepository;
import com.attendance.Repository.SubjectRepository;
import com.attendance.Repository.UserRepository;
import com.attendance.ServiceInterface.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    private MapStructMapper mapStructMapper;
    private final Message message = new Message();

    @Override
    public void createClass(ClassEditDTO classEditDTO) throws ResourceNotFoundException {
        AppUser teacher = userRepository.findById(classEditDTO.getTeacherId())
                        .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND));
        Subject subject = subjectRepository.findById(classEditDTO.getSubjectId())
                        .orElseThrow(() -> new ResourceNotFoundException(message.SUBJECT_NOT_FOUND));
        // Check isTeacher
        Class newClass = mapStructMapper.MAPPER.editToEntity(classEditDTO);
        newClass.setId(null);
        newClass.setTeacher(teacher);
        newClass.setSubject(subject);

        classRepository.saveAndFlush(newClass);
    }

    @Override
    public void updateClass(UUID classId, ClassEditDTO classEditDTO) throws ResourceNotFoundException {
        Class foundClass = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException(message.CLASS_NOT_FOUND + classId));
        AppUser teacher = userRepository.findById(classEditDTO.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND));
        Subject subject = subjectRepository.findById(classEditDTO.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException(message.SUBJECT_NOT_FOUND));
        // Check isTeacher
        foundClass.setName(classEditDTO.getName());
        foundClass.setStartDate(classEditDTO.getStartDate());
        foundClass.setTeacher(teacher);
        foundClass.setSubject(subject);

        classRepository.saveAndFlush(foundClass);
    }

    @Override
    public void deleteClass(UUID classId) throws ResourceNotFoundException {
        Class foundClass = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException(message.CLASS_NOT_FOUND + classId));
        classRepository.delete(foundClass);
    }

    @Override
    public ClassDTO getClass(UUID classId) throws ResourceNotFoundException {
        Class foundClass = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException(message.CLASS_NOT_FOUND + classId));
        return mapStructMapper.MAPPER.toDto(foundClass);
    }

    @Override
    public Collection<ClassDTO> getClasses() throws ResourceNotFoundException {
        Collection<Class> classes = classRepository.findAll();

        if (classes.isEmpty()) {
            throw new ResourceNotFoundException(message.NO_CLASS_FOUND);
        }

        return classes.stream().map(mapStructMapper.MAPPER::toDto).collect(Collectors.toList());
    }
}
