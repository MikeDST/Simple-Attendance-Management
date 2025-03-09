package com.attendance.ServiceImpl;

import com.attendance.Common.Message;
import com.attendance.DTO.RegisterDTO;
import com.attendance.DTO.UserCreateDTO;
import com.attendance.DTO.UserDTO;
import com.attendance.Entity.AppUser;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.Mapper.MapStructMapper;
import com.attendance.Repository.UserRepository;
import com.attendance.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
            System.out.println("hii");
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.findByEmail(createUserDTO.getEmail()).isPresent()) {
            System.out.println("hello");
            throw new RuntimeException("Email already in use");
        }
        createUserDTO.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        System.out.println("yooo");
        userRepository.saveAndFlush(MapStructMapper.MAPPER.createToEntity(createUserDTO));
    }

    @Override
    public void updateUser(UUID userId, RegisterDTO registerDTO) throws ResourceNotFoundException {
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

    @Override
    public void deleteUser(UUID userId) throws ResourceNotFoundException {
        AppUser foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND + userId));
        userRepository.delete(foundUser);
    }

    @Override
    public UserDTO getUser(UUID userId) throws ResourceNotFoundException {
        AppUser foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND + userId));
        return MapStructMapper.MAPPER.toDto(foundUser);
    }

    @Override
    public Collection<UserDTO> getUsers() throws ResourceNotFoundException {
        Collection<AppUser> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException(message.NO_USER_FOUND);
        }

        return users.stream().map(MapStructMapper.MAPPER::toDto).collect(Collectors.toList());
    }
}
