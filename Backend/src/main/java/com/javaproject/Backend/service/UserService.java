package com.javaproject.Backend.service;

import com.javaproject.Backend.dto.response.RegisterResponse;
import java.util.List;

/**
 * Interface định nghĩa các phương thức thao tác trực tiếp với User
 */
public interface UserService {

    // Lấy thông tin user theo ID
    RegisterResponse getUserById(Long userId);

    // Lấy danh sách tất cả user
    List<RegisterResponse> getAllUsers();

    RegisterResponse updateUser(Long userId, RegisterResponse request);
}
