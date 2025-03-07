package com.attendance.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterDTO {
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("username")
    private String username;

    @NotNull
    @Email(message = "Email is not valid", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("password")
    private String password;
}
