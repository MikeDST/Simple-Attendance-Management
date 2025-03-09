package com.attendance.ServiceInterface;

import com.attendance.DTO.AttendanceDTO;
import com.attendance.DTO.AttendanceEditDTO;
import com.attendance.Exception.ResourceNotFoundException;

import java.util.Collection;
import java.util.UUID;

public interface AttendanceService {
    void createAttendance(AttendanceEditDTO attendanceEditDTO) throws ResourceNotFoundException;
    void updateAttendance(UUID attendanceId, AttendanceEditDTO attendanceEditDTO) throws ResourceNotFoundException;
    void deleteAttendance(UUID attendanceId) throws ResourceNotFoundException;
    AttendanceDTO getAttendance(UUID attendanceId) throws ResourceNotFoundException;
    Collection<AttendanceDTO> getAttendances() throws ResourceNotFoundException;
}