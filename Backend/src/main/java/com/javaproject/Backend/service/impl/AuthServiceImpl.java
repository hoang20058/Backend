package com.javaproject.Backend.service.impl;

import com.javaproject.Backend.domain.User;
import com.javaproject.Backend.dto.request.LoginRequest;
import com.javaproject.Backend.dto.request.RegisterRequest;
import com.javaproject.Backend.dto.response.JwtResponse;
import com.javaproject.Backend.dto.response.RegisterResponse;
import com.javaproject.Backend.repository.UserRepository;
import com.javaproject.Backend.service.AuthService;
import com.javaproject.Backend.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service triển khai nghiệp vụ đăng ký/đăng nhập
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Mã hóa mật khẩu
    private final JwtTokenUtil jwtTokenUtil;       // Sinh và validate JWT

    @Override
    public RegisterResponse register(RegisterRequest request) {
        // Kiểm tra email đã tồn tại
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Tạo user mới
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        // Lưu vào DB
        userRepository.save(user);

        // Trả về DTO
        RegisterResponse response = new RegisterResponse();
        response.setUserId(user.getUserId());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        return response;
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        // Tìm user theo email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra mật khẩu
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Tạo JWT token
        String token = jwtTokenUtil.generateToken(user.getEmail());

        return new JwtResponse(token, user.getUserId(), user.getEmail());
    }
}
