package com.attendance.ServiceImpl;

import com.attendance.Common.Message;
import com.attendance.DTO.ChangePasswordDTO;
import com.attendance.DTO.RegisterDTO;
import com.attendance.DTO.UserCreateDTO;
import com.attendance.DTO.UserDTO;
import com.attendance.Entity.AppUser;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.Mapper.MapStructMapper;
import com.attendance.Repository.UserRepository;
import com.attendance.ServiceInterface.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final Message message = new Message();

    @Override
    public void registerUser(RegisterDTO registerDTO) {
        if (userRepository.findByUserName(registerDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userRepository.saveAndFlush(MapStructMapper.MAPPER.registerToEntity(registerDTO));
    }

    @Override
    public void createUser(UserCreateDTO createUserDTO) {
        if (userRepository.findByUserName(createUserDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.findByEmail(createUserDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        createUserDTO.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        userRepository.saveAndFlush(MapStructMapper.MAPPER.createToEntity(createUserDTO));
    }

    @SneakyThrows
    @Override
    public void updateUser(UUID userId, RegisterDTO registerDTO){
        AppUser foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND + userId));

        foundUser.setUserName(registerDTO.getUsername());
        foundUser.setEmail(registerDTO.getEmail());
        foundUser.setPassword(registerDTO.getPassword());
        foundUser.setFirstName(registerDTO.getFirstName());
        foundUser.setLastName(registerDTO.getLastName());
        foundUser.setPhone(registerDTO.getPhone());

        userRepository.saveAndFlush(foundUser);
    }

    @SneakyThrows
    @Override
    public void changePassword(UUID userId, ChangePasswordDTO changePasswordDTO){
        AppUser foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND + userId));
        if(!Objects.equals(foundUser.getPassword(), passwordEncoder.encode(changePasswordDTO.getCurrentPassword())))
        {
            throw new RuntimeException("Wrong password");
        }
        if(!Objects.equals(changePasswordDTO.getNewPassword(), changePasswordDTO.getNewPasswordConfirm()))
        {
            throw new RuntimeException("New password and confirmed password do not match");
        }

        foundUser.setPassword(changePasswordDTO.getNewPasswordConfirm());
        userRepository.saveAndFlush(foundUser);
    }

    @SneakyThrows
    @Override
    public void deleteUser(UUID userId){
        AppUser foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND + userId));
        userRepository.delete(foundUser);
    }

    @SneakyThrows
    @Override
    public UserDTO getUser(UUID userId){
        AppUser foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND + userId));
        return MapStructMapper.MAPPER.toDto(foundUser);
    }

    @SneakyThrows
    @Override
    public Collection<UserDTO> getUsers(){
        Collection<AppUser> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException(message.NO_USER_FOUND);
        }

        return users.stream().map(MapStructMapper.MAPPER::toDto).collect(Collectors.toList());
    }
}
