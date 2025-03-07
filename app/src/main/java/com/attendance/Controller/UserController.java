package com.attendance.Controller;

import com.attendance.DTO.UserSignInDTO;
import com.attendance.Entity.AppUser;
import com.attendance.Repository.UserRepository;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    private AuthenticationService authenticationService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid AppUser request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setRole("STUDENT");
        return ResponseEntity.ok(userRepository.saveAndFlush(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody UserSignInDTO userSignInDTO, HttpSession session)
    {
        // Get authentication
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userSignInDTO.getUsername(), userSignInDTO.getPassword()));
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
}
