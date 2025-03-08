package com.attendance.ServiceImpl;

import com.attendance.Common.Message;
import com.attendance.DTO.AttendanceDTO;
import com.attendance.Entity.Attendance;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.Mapper.MapStructMapper;
import com.attendance.Repository.AttendanceRepository;
import com.attendance.ServiceInterface.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    private MapStructMapper mapStructMapper;
    private final Message message = new Message();

    @Override
    public void createAttendance(AttendanceDTO attendanceDTO) {
        attendanceDTO.setId(null);
        attendanceRepository.saveAndFlush(mapStructMapper.MAPPER.toEntity(attendanceDTO));
    }

    @Override
    public void updateAttendance(UUID attendanceId, AttendanceDTO attendanceDTO) throws ResourceNotFoundException {
        Attendance foundAttendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException(message.ATTENDANCE_NOT_FOUND + attendanceId));

        // foundAttendance.setStatus(attendanceDTO.getStatus());
        attendanceRepository.saveAndFlush(foundAttendance);
    }

    @Override
    public void deleteAttendance(UUID attendanceId) throws ResourceNotFoundException {
        Attendance foundAttendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException(message.ATTENDANCE_NOT_FOUND + attendanceId));
        attendanceRepository.delete(foundAttendance);
    }

    @Override
    public AttendanceDTO getAttendance(UUID attendanceId) throws ResourceNotFoundException {
        Attendance foundAttendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException(message.ATTENDANCE_NOT_FOUND + attendanceId));
        return mapStructMapper.MAPPER.toDto(foundAttendance);
    }

    @Override
    public Collection<AttendanceDTO> getAttendances() throws ResourceNotFoundException {
        Collection<Attendance> attendances = attendanceRepository.findAll();

        if (attendances.isEmpty()) {
            throw new ResourceNotFoundException(message.NO_ATTENDANCE_FOUND);
        }

        return attendances.stream().map(mapStructMapper.MAPPER::toDto).collect(Collectors.toList());
    }
}
