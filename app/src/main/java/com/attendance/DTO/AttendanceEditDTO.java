package com.attendance.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttendanceEditDTO {
    @JsonProperty("studentid")
    private UUID studentId;

    @JsonProperty("classid")
    private UUID classId;

    @JsonProperty("joindate")
    private LocalDateTime joinDate;

    @JsonProperty("grade")
    @PositiveOrZero(message = "Grade must be greater than or equal to 0")
    private double grade;

    @JsonProperty("isattended")
    private boolean isAttended;
}
