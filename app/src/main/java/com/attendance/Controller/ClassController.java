package com.attendance.Controller;

import com.attendance.Common.Message;
import com.attendance.Common.SuccessDetails;
import com.attendance.DTO.ClassDTO;
import com.attendance.DTO.ClassEditDTO;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.ServiceInterface.ClassService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@RestController
public class ClassController {
    @Autowired
    private ClassService classService;
    private final SuccessDetails successDetails = new SuccessDetails();
    private final Message message = new Message();

    @GetMapping("/classes")
    public ResponseEntity<Object> getAllClasses() throws ResourceNotFoundException {
        Collection<ClassDTO> classes = classService.getClasses();
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.OPERATION_COMPLETED,
                classes
        );
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @GetMapping("/class/{id}")
    public ResponseEntity<Object> getClass(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        ClassDTO classDTO = classService.getClass(id);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.OPERATION_COMPLETED,
                classDTO
        );
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @PostMapping("/class/create")
    public ResponseEntity<Object> createClass(@RequestBody @Valid ClassEditDTO classEditDTO) throws ResourceNotFoundException {
        classService.createClass(classEditDTO);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.CLASS_CREATED,
                null);
        return new ResponseEntity<>(successDetails, HttpStatus.CREATED);
    }

    @PutMapping("/class/update/{id}")
    public ResponseEntity<Object> updateClass(@PathVariable("id") UUID id, @RequestBody @Valid ClassEditDTO classEditDTO) throws ResourceNotFoundException {
        classService.updateClass(id, classEditDTO);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.CLASS_UPDATED,
                null);
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @DeleteMapping("/class/delete/{id}")
    public ResponseEntity<Object> deleteClass(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        classService.deleteClass(id);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.CLASS_DELETED,
                null);
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }
}
