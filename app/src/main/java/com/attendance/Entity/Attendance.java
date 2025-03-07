package com.attendance.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@Entity
@Table(name = "attendances")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "join_date", nullable = false)
    private LocalDateTime joinDate;

    private double grade;

    @Column(name = "is_attended", nullable = false)
    private boolean isAttended;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private AppUser student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private Class aClass;
}
