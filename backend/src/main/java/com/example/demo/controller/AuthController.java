package com.example.demo.controller;

import com.example.demo.domain.user.*;
import com.example.demo.util.JwtUtil;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public void signup(@RequestBody AuthRequest request) {
        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow();

        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    record AuthRequest(String email, String password) {}
}
