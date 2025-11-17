package com.javaproject.Backend.service.impl;

import com.javaproject.Backend.domain.Budget;
import com.javaproject.Backend.domain.Category;
import com.javaproject.Backend.domain.User;
import com.javaproject.Backend.dto.request.BudgetRequest;
import com.javaproject.Backend.dto.response.BudgetResponse;
import com.javaproject.Backend.repository.BudgetRepository;
import com.javaproject.Backend.repository.CategoryRepository;
import com.javaproject.Backend.repository.UserRepository;
import com.javaproject.Backend.service.BudgetService;
import com.javaproject.Backend.util.dtoMapper.BudgetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service triển khai nghiệp vụ cho Budget
 */
@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public BudgetResponse createBudget(BudgetRequest request) {
        // Kiểm tra user tồn tại
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra category tồn tại
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Map từ DTO → Entity
        Budget budget = BudgetMapper.toEntity(request);
        budget.setUser(user);
        budget.setCategory(category);

        // Lưu vào DB
        budgetRepository.save(budget);

        // Trả về DTO
        return BudgetMapper.toDto(budget);
    }

    @Override
    public BudgetResponse updateBudget(Long budgetId, BudgetRequest request) {
        // Lấy budget từ DB
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        // Kiểm tra category mới (nếu muốn thay đổi)
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Cập nhật entity từ DTO
        BudgetMapper.updateFromRequest(budget, request);
        budget.setCategory(category);

        budgetRepository.save(budget);

        return BudgetMapper.toDto(budget);
    }

    @Override
    public void deleteBudget(Long budgetId) {
        if (!budgetRepository.existsById(budgetId)) {
            throw new RuntimeException("Budget not found");
        }
        budgetRepository.deleteById(budgetId);
    }

    @Override
    public List<BudgetResponse> getBudgetsByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        Optional<Budget> budgets = budgetRepository.findById(userId);

        return budgets.stream()
                .map(BudgetMapper::toDto)
                .collect(Collectors.toList());
    }
}
