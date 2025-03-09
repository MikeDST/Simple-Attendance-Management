package com.attendance.Controller;

import com.attendance.Common.Message;
import com.attendance.Common.SuccessDetails;
import com.attendance.DTO.*;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.ServiceInterface.UserService;
import com.attendance.authen.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private AuthenticationService authenticationService;
    private PasswordEncoder passwordEncoder;
    private final Message message = new Message();

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO) {
        userService.registerUser(registerDTO);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.USER_CREATED,
                null);
        return new ResponseEntity<>(successDetails, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LogInDTO userLogInDTO, HttpSession session)
    {
        // Get authentication
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogInDTO.getUserName(), userLogInDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Store in session
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return new ResponseEntity<>("Signed-in successfully!", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok("Logged out successfully!");
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() throws ResourceNotFoundException {
        Collection<UserDTO> users = userService.getUsers();
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.OPERATION_COMPLETED,
                users
        );
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getSubject(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        UserDTO user = userService.getUser(id);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.OPERATION_COMPLETED,
                user
        );
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @PostMapping("/user/create")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        userService.createUser(userCreateDTO);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.USER_CREATED,
                null);
        return new ResponseEntity<>(successDetails, HttpStatus.CREATED);
    }

    @PutMapping(value = "/user/update/{id}")
    public ResponseEntity<Object> updateSubject(@PathVariable("id") UUID id, @RequestBody @Valid RegisterDTO registerDTO) throws ResourceNotFoundException {
        userService.updateUser(id, registerDTO);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.USER_UPDATED,
                null);
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/delete/{id}")
    public ResponseEntity<Object> deleteSubject(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        userService.deleteUser(id);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                message.USER_DELETED,
                null);
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }
}
