package com.attendance.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@Entity
@Table(name = "classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private AppUser teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @OneToMany(mappedBy="aClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendances;
}
