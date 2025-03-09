package com.attendance.Controller;

import com.attendance.Common.Message;
import com.attendance.Common.SuccessDetails;
import com.attendance.DTO.AttendanceDTO;
import com.attendance.DTO.AttendanceEditDTO;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.ServiceInterface.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@RestController
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    private final SuccessDetails successDetails = new SuccessDetails();
    private final Message message = new Message();

    @GetMapping("/attendances")
    public ResponseEntity<Object> getAllAttendances(){
        Collection<AttendanceDTO> attendances = attendanceService.getAttendances();
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.OPERATION_COMPLETED,
                attendances
        );
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @GetMapping("/attendances/{id}")
    public ResponseEntity<Object> getAttendance(@PathVariable("id") UUID id){
        AttendanceDTO attendance = attendanceService.getAttendance(id);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.OPERATION_COMPLETED,
                attendance
        );
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @GetMapping("/student/attendances/{id}")
    public ResponseEntity<Object> getMyAttendances(@PathVariable("id") UUID id){
        Collection<AttendanceDTO> attendances = attendanceService.getMyAttendances(id);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.OPERATION_COMPLETED,
                attendances
        );
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @PostMapping("/attendance/create")
    public ResponseEntity<Object> createAttendance(@RequestBody @Valid AttendanceEditDTO attendanceEditDTO){
        attendanceService.createAttendance(attendanceEditDTO);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.ATTENDANCE_CREATED,
                null);
        return new ResponseEntity<>(successDetails, HttpStatus.CREATED);
    }

    @PutMapping("/attendance/update/{id}")
    public ResponseEntity<Object> updateAttendance(@PathVariable("id") UUID id, @RequestBody @Valid AttendanceEditDTO attendanceEditDTO){
        attendanceService.updateAttendance(id, attendanceEditDTO);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.ATTENDANCE_UPDATED,
                null);
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @DeleteMapping("/attendance/delete/{id}")
    public ResponseEntity<Object> deleteAttendance(@PathVariable("id") UUID id){
        attendanceService.deleteAttendance(id);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.ATTENDANCE_DELETED,
                null);
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }
}
