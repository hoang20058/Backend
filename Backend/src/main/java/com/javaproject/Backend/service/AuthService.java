package com.javaproject.Backend.service;

import com.javaproject.Backend.dto.request.LoginRequest;
import com.javaproject.Backend.dto.request.RegisterRequest;
import com.javaproject.Backend.dto.response.JwtResponse;
import com.javaproject.Backend.dto.response.RegisterResponse;

/**
 * Interface định nghĩa nghiệp vụ đăng ký và đăng nhập
 */
public interface AuthService {

    // Đăng ký tài khoản mới
    RegisterResponse register(RegisterRequest request);

    // Đăng nhập và trả về JWT
    JwtResponse login(LoginRequest request);
}
