package com.example.demo.service;

import com.example.demo.domain.user.LoginRequest;
import com.example.demo.domain.user.SignupRequest;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public void signup(SignupRequest request) {
        String encoded = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getEmail(), encoded);
        userRepository.save(user);
    }

    public  String login(LoginRequest request) {

        System.out.println("LOGIN email = [" + request.getEmail() + "]");
        System.out.println("LOGIN password = [" + request.getPassword() + "]");

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("잘못된 비밀번호 입니다.");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

}
