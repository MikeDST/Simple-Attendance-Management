package com.attendance.Controller;

import com.attendance.Common.Message;
import com.attendance.Common.SuccessDetails;
import com.attendance.DTO.BeanDTO;
import com.attendance.DTO.SubjectDTO;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.ServiceInterface.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@RestController
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    private final SuccessDetails successDetails = new SuccessDetails();
    private final Message message = new Message();

    @GetMapping("/subjects")
    public ResponseEntity<Object> getAllSubjects() throws ResourceNotFoundException {
        Collection<SubjectDTO> subjects = subjectService.getSubjects();
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.OPERATION_COMPLETED,
                subjects
        );
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<Object> getSubject(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        SubjectDTO subject = subjectService.getSubject(id);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.OPERATION_COMPLETED,
                subject
        );
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @PostMapping("/subject/create")
    public ResponseEntity<Object> createSubject(@RequestBody @Valid SubjectDTO subjectDTO) {
        subjectService.createSubject(subjectDTO);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.SUBJECT_CREATED,
                null);
        return new ResponseEntity<>(successDetails, HttpStatus.CREATED);
    }

    @PutMapping(value = "/subject/update/{id}")
    public ResponseEntity<Object> updateSubject(@PathVariable("id") UUID id, @RequestBody @Valid SubjectDTO subjectDTO) throws ResourceNotFoundException {
        subjectService.updateSubject(id, subjectDTO);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.SUBJECT_UPDATED,
                null);
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @DeleteMapping(value = "/subject/delete/{id}")
    public ResponseEntity<Object> deleteSubject(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        subjectService.deleteSubject(id);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.SUBJECT_DELETED,
                null);
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }
}
