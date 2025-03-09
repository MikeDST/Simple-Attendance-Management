package com.attendance.ServiceInterface;

import com.attendance.DTO.ChangePasswordDTO;
import com.attendance.DTO.RegisterDTO;
import com.attendance.DTO.UserCreateDTO;
import com.attendance.DTO.UserDTO;
import com.attendance.Entity.AppUser;
import com.attendance.Exception.ResourceNotFoundException;

import java.util.Collection;
import java.util.UUID;

public interface UserService {
    void registerUser(RegisterDTO registerDTO);
    void createUser(UserCreateDTO createUserDTO);
    void updateUser(UUID userId, RegisterDTO registerDTO);
    void changePassword(UUID userId, ChangePasswordDTO changePasswordDTO);
    void deleteUser(UUID userId);
    UserDTO getUser(UUID userId);
    Collection<UserDTO> getUsers();
}
