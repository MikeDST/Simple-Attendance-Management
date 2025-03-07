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
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @OneToMany(mappedBy="subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Class> classes;
}
