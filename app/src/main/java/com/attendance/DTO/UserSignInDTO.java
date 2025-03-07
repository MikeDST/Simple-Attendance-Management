package com.attendance.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSignInDTO {
    @NotNull
    @JsonProperty("username")
    private String userName;

    @NotNull
    @JsonProperty("password")
    private String password;
}
