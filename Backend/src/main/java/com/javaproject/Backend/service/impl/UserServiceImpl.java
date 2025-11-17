package com.javaproject.Backend.service.impl;

import com.javaproject.Backend.domain.User;
import com.javaproject.Backend.dto.response.RegisterResponse;
import com.javaproject.Backend.repository.UserRepository;
import com.javaproject.Backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service thao tác trực tiếp với User entity
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public RegisterResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RegisterResponse response = new RegisterResponse();
        response.setUserId(user.getUserId());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        return response;
    }

    public List<RegisterResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    RegisterResponse dto = new RegisterResponse();
                    dto.setUserId(user.getUserId());
                    dto.setEmail(user.getEmail());
                    dto.setFullName(user.getFullName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public RegisterResponse updateUser(Long userId, RegisterResponse request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }
}
