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
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
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
    private final Message message = new Message();

    private Boolean isTeacher(AppUser user)
    {
        return Objects.equals(user.getRole(), "TEACHER");
    }

    @SneakyThrows
    @Override
    public void createClass(ClassEditDTO classEditDTO){
        AppUser teacher = userRepository.findById(classEditDTO.getTeacherId())
                        .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND));
        if(!isTeacher(teacher)) throw new ResourceNotFoundException(message.IS_NOT_TEACHER);
        Subject subject = subjectRepository.findById(classEditDTO.getSubjectId())
                        .orElseThrow(() -> new ResourceNotFoundException(message.SUBJECT_NOT_FOUND));

        Class newClass = MapStructMapper.MAPPER.editToEntity(classEditDTO);
        newClass.setId(null);
        newClass.setTeacher(teacher);
        newClass.setSubject(subject);

        classRepository.saveAndFlush(newClass);
    }

    @SneakyThrows
    @Override
    public void updateClass(UUID classId, ClassEditDTO classEditDTO){
        Class foundClass = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException(message.CLASS_NOT_FOUND + classId));
        AppUser teacher = userRepository.findById(classEditDTO.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND));
        if(!isTeacher(teacher)) throw new ResourceNotFoundException(message.IS_NOT_TEACHER);
        Subject subject = subjectRepository.findById(classEditDTO.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException(message.SUBJECT_NOT_FOUND));

        foundClass.setName(classEditDTO.getName());
        foundClass.setStartDate(classEditDTO.getStartDate());
        foundClass.setTeacher(teacher);
        foundClass.setSubject(subject);

        classRepository.saveAndFlush(foundClass);
    }

    @SneakyThrows
    @Override
    public void deleteClass(UUID classId){
        Class foundClass = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException(message.CLASS_NOT_FOUND + classId));
        classRepository.delete(foundClass);
    }

    @SneakyThrows
    @Override
    public ClassDTO getClass(UUID classId){
        Class foundClass = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException(message.CLASS_NOT_FOUND + classId));
        return MapStructMapper.MAPPER.toDto(foundClass);
    }

    @SneakyThrows
    @Override
    public Collection<ClassDTO> getMyClasses(UUID myId){
        AppUser teacher = userRepository.findById(myId)
                .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND));
        if(!isTeacher(teacher)) throw new ResourceNotFoundException(message.IS_NOT_TEACHER);

        Collection<Class> classes = classRepository.findMyClasses(myId);
        if (classes.isEmpty()) {
            throw new ResourceNotFoundException(message.NO_CLASS_FOUND);
        }
        return classes.stream().map(MapStructMapper.MAPPER::toDto).collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public Collection<ClassDTO> getClasses(){
        Collection<Class> classes = classRepository.findAll();

        if (classes.isEmpty()) {
            throw new ResourceNotFoundException(message.NO_CLASS_FOUND);
        }

        return classes.stream().map(MapStructMapper.MAPPER::toDto).collect(Collectors.toList());
    }
}
