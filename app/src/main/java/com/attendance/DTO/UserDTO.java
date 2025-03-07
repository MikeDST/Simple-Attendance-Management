package com.attendance.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("username")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("role")
    private String role;


}
