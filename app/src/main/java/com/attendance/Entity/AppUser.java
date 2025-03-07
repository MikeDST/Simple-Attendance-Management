package com.attendance.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String phone;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy="teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Class> classes;

    @OneToMany(mappedBy="student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendances;
}
