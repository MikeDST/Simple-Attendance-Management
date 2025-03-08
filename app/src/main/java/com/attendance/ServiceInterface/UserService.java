package com.attendance.ServiceInterface;

import com.attendance.DTO.RegisterDTO;
import com.attendance.DTO.UserDTO;
import com.attendance.Entity.AppUser;
import com.attendance.Exception.ResourceNotFoundException;

import java.util.Collection;
import java.util.UUID;

public interface UserService {
    void registerUser(RegisterDTO registerDTO);
    void updateUser(UUID userId, UserDTO userDTO) throws ResourceNotFoundException;
    void deleteUser(UUID userId) throws ResourceNotFoundException;
    UserDTO getUser(UUID userId) throws ResourceNotFoundException;
    Collection<UserDTO> getUsers() throws ResourceNotFoundException;
}
