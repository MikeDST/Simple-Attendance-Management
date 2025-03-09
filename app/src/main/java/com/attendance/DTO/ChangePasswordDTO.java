package com.attendance.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangePasswordDTO {
    @NotNull
    @JsonProperty("currentpassword")
    private String currentPassword;

    @NotNull
    @JsonProperty("newpassword")
    private String newPassword;

    @NotNull
    @JsonProperty("newpasswordconfirm")
    private String newPasswordConfirm;
}
