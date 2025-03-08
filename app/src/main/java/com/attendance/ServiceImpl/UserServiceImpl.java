package com.attendance.ServiceImpl;

import com.attendance.Common.Message;
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
    private PasswordEncoder passwordEncoder;
    private MapStructMapper mapStructMapper;
    private final Message message = new Message();

    @Override
    public AppUser registerUser(String username, String email, String password) {
        if (userRepository.findByUserName(username).isPresent()) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        AppUser appUser = new AppUser();
        appUser.setUserName(username);
        appUser.setEmail(email);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setRole("USER");

        return userRepository.save(appUser);
    }

    @Override
    public void updateUser(UUID userId, UserDTO userDTO) throws ResourceNotFoundException {
        AppUser foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(message.USER_NOT_FOUND + userId));

        // foundUser.setName(userDTO.getName());
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
        return mapStructMapper.MAPPER.toDto(foundUser);
    }

    @Override
    public Collection<UserDTO> getUsers() throws ResourceNotFoundException {
        Collection<AppUser> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new ResourceNotFoundException(message.NO_USER_FOUND);
        }

        return users.stream().map(mapStructMapper.MAPPER::toDto).collect(Collectors.toList());
    }
}
