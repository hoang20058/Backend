package com.javaproject.Backend.service;

import com.javaproject.Backend.dto.request.ExpenseRequest;
import com.javaproject.Backend.dto.response.ExpenseResponse;
import java.util.List;

/**
 * Interface định nghĩa các phương thức CRUD cho Expense
 */
public interface ExpenseService {

    // Tạo mới một Expense
    ExpenseResponse createExpense(ExpenseRequest request);

    // Cập nhật Expense theo ID
    ExpenseResponse updateExpense(Long expenseId, ExpenseRequest request);

    // Xóa Expense theo ID
    void deleteExpense(Long expenseId);

    // Lấy danh sách Expense theo User
    List<ExpenseResponse> getExpensesByUser(Long userId);
}
