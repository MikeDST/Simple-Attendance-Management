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
public class ClassEditDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("startdate")
    @PastOrPresent(message = "Publish date must be in the past or present")
    private LocalDateTime startDate;

    @JsonProperty("teacherid")
    private UUID teacherId;

    @JsonProperty("subjectid")
    private UUID subjectId;
}
