package com.rental.rental.video.system.service;

import com.rental.rental.video.system.dto.AuthResponse;
import com.rental.rental.video.system.dto.LoginRequest;
import com.rental.rental.video.system.dto.RegisterRequest;
import com.rental.rental.video.system.entity.Role;
import com.rental.rental.video.system.entity.User;
import com.rental.rental.video.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(request.getRole() != null ? request.getRole() : Role.CUSTOMER)
                .build();

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
