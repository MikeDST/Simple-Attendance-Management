package com.attendance.Repository;

import com.attendance.Entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    @Query(value = "select * from attendances a where a.student_id = ?1", nativeQuery = true)
    Collection<Attendance> findMyAttendances(UUID myId);
}
