package com.javaproject.Backend.service;

import com.javaproject.Backend.dto.request.BudgetRequest;
import com.javaproject.Backend.dto.response.BudgetResponse;
import java.util.List;

/**
 * Interface định nghĩa các phương thức CRUD cho Budget
 */
public interface BudgetService {

    BudgetResponse createBudget(BudgetRequest request);

    BudgetResponse updateBudget(Long budgetId, BudgetRequest request);

    void deleteBudget(Long budgetId);

    List<BudgetResponse> getBudgetsByUser(Long userId);
}
