package com.attendance.ServiceImpl;

import com.attendance.Common.Message;
import com.attendance.DTO.ClassDTO;
import com.attendance.Entity.Class;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.Mapper.MapStructMapper;
import com.attendance.Repository.ClassRepository;
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
    private MapStructMapper mapStructMapper;
    private final Message message = new Message();

    @Override
    public void createClass(ClassDTO classDTO) {
        classDTO.setId(null);
        classRepository.saveAndFlush(mapStructMapper.MAPPER.toEntity(classDTO));
    }

    @Override
    public void updateClass(UUID classId, ClassDTO classDTO) throws ResourceNotFoundException {
        Class foundClass = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException(message.CLASS_NOT_FOUND + classId));

        // foundClass.setName(classDTO.getName());
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
