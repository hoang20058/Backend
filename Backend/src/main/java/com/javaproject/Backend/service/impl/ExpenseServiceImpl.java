package com.javaproject.Backend.service.impl;

import com.javaproject.Backend.domain.Category;
import com.javaproject.Backend.domain.Expense;
import com.javaproject.Backend.domain.User;
import com.javaproject.Backend.dto.request.ExpenseRequest;
import com.javaproject.Backend.dto.response.ExpenseResponse;
import com.javaproject.Backend.repository.CategoryRepository;
import com.javaproject.Backend.repository.ExpenseRepository;
import com.javaproject.Backend.repository.UserRepository;
import com.javaproject.Backend.service.ExpenseService;
import com.javaproject.Backend.util.dtoMapper.ExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service triển khai nghiệp vụ cho Expense
 */
@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ExpenseResponse createExpense(ExpenseRequest request) {
        // Kiểm tra user tồn tại
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra category tồn tại (nếu có)
        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        }

        // Map từ DTO → Entity
        Expense expense = ExpenseMapper.toEntity(request);
        expense.setUser(user);
        expense.setCategory(category);

        // Lưu vào database
        expenseRepository.save(expense);

        // Trả về DTO
        return ExpenseMapper.toDto(expense);
    }

    @Override
    public ExpenseResponse updateExpense(Long expenseId, ExpenseRequest request) {
        // Lấy expense từ DB
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        // Kiểm tra category mới (nếu có)
        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        }

        // Cập nhật entity từ DTO
        ExpenseMapper.updateFromRequest(expense, request);
        expense.setCategory(category);

        expenseRepository.save(expense);

        return ExpenseMapper.toDto(expense);
    }

    @Override
    public void deleteExpense(Long expenseId) {
        if (!expenseRepository.existsById(expenseId)) {
            throw new RuntimeException("Expense not found");
        }
        expenseRepository.deleteById(expenseId);
    }

    @Override
    public List<ExpenseResponse> getExpensesByUser(Long userId) {
        // Kiểm tra user tồn tại
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        // Lấy danh sách Expense của user
        Optional<Expense> expenses = expenseRepository.findById(userId);

        // Map entity → DTO
        return expenses.stream()
                .map(ExpenseMapper::toDto)
                .collect(Collectors.toList());
    }
}
