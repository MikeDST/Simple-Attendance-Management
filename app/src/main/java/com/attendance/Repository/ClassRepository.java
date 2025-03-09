package com.attendance.Repository;

import com.attendance.Entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<Class, UUID> {
    @Query(value = "select * from classes a where a.teacher_id = ?1", nativeQuery = true)
    Collection<Class> findMyClasses(UUID myId);
}
