package com.attendance.ServiceImpl;

import com.attendance.Common.Message;
import com.attendance.DTO.AttendanceDTO;
import com.attendance.DTO.AttendanceEditDTO;
import com.attendance.Entity.AppUser;
import com.attendance.Entity.Attendance;
import com.attendance.Entity.Class;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.Mapper.MapStructMapper;
import com.attendance.Repository.AttendanceRepository;
import com.attendance.Repository.ClassRepository;
import com.attendance.Repository.UserRepository;
import com.attendance.ServiceInterface.AttendanceService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassRepository classRepository;
    private final Message message = new Message();

    private Boolean isStudent(AppUser user)
    {
        return Objects.equals(user.getRole(), "STUDENT");
    }

    @SneakyThrows
    @Override
    public void createAttendance(AttendanceEditDTO attendanceEditDTO){
        AppUser student = userRepository.findById(attendanceEditDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND));
        if(!isStudent(student)) throw new ResourceNotFoundException(message.IS_NOT_STUDENT);
        Class aClass = classRepository.findById(attendanceEditDTO.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException(message.CLASS_NOT_FOUND));

        Attendance attendance = MapStructMapper.MAPPER.editToEntity(attendanceEditDTO);
        attendance.setId(null);
        attendance.setStudent(student);
        attendance.setAClass(aClass);

        attendanceRepository.saveAndFlush(attendance);
    }

    @SneakyThrows
    @Override
    public void updateAttendance(UUID attendanceId, AttendanceEditDTO attendanceEditDTO){
        Attendance foundAttendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException(message.ATTENDANCE_NOT_FOUND + attendanceId));
        AppUser student = userRepository.findById(attendanceEditDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND));
        if(!isStudent(student)) throw new ResourceNotFoundException(message.IS_NOT_STUDENT);
        Class aClass = classRepository.findById(attendanceEditDTO.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException(message.CLASS_NOT_FOUND));

        foundAttendance.setJoinDate(attendanceEditDTO.getJoinDate());
        foundAttendance.setGrade(attendanceEditDTO.getGrade());
        foundAttendance.setAttended(attendanceEditDTO.isAttended());
        foundAttendance.setStudent(student);
        foundAttendance.setAClass(aClass);

        attendanceRepository.saveAndFlush(foundAttendance);
    }

    @SneakyThrows
    @Override
    public void deleteAttendance(UUID attendanceId){
        Attendance foundAttendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException(message.ATTENDANCE_NOT_FOUND + attendanceId));
        attendanceRepository.delete(foundAttendance);
    }

    @SneakyThrows
    @Override
    public AttendanceDTO getAttendance(UUID attendanceId){
        Attendance foundAttendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException(message.ATTENDANCE_NOT_FOUND + attendanceId));
        return MapStructMapper.MAPPER.toDto(foundAttendance);
    }

    @SneakyThrows
    @Override
    public Collection<AttendanceDTO> getMyAttendances(UUID myId){
        AppUser student = userRepository.findById(myId)
                .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND));
        if(!isStudent(student)) throw new ResourceNotFoundException(message.IS_NOT_STUDENT);

        Collection<Attendance> attendances = attendanceRepository.findMyAttendances(myId);
        if (attendances.isEmpty()) {
            throw new ResourceNotFoundException(message.NO_ATTENDANCE_FOUND);
        }

        return attendances.stream().map(MapStructMapper.MAPPER::toDto).collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public Collection<AttendanceDTO> getAttendances(){
        Collection<Attendance> attendances = attendanceRepository.findAll();

        if (attendances.isEmpty()) {
            throw new ResourceNotFoundException(message.NO_ATTENDANCE_FOUND);
        }

        return attendances.stream().map(MapStructMapper.MAPPER::toDto).collect(Collectors.toList());
    }
}
