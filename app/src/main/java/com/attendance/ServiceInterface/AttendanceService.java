package com.attendance.ServiceInterface;

import com.attendance.DTO.AttendanceDTO;
import com.attendance.DTO.AttendanceEditDTO;
import com.attendance.Exception.ResourceNotFoundException;

import java.util.Collection;
import java.util.UUID;

public interface AttendanceService {
    void createAttendance(AttendanceEditDTO attendanceEditDTO);
    void updateAttendance(UUID attendanceId, AttendanceEditDTO attendanceEditDTO);
    void deleteAttendance(UUID attendanceId);
    AttendanceDTO getAttendance(UUID attendanceId);
    Collection<AttendanceDTO> getMyAttendances(UUID myId);
    Collection<AttendanceDTO> getAttendances();
}