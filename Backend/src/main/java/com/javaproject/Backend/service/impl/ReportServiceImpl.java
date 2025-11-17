package com.javaproject.Backend.service.impl;

import com.javaproject.Backend.dto.request.ReportRequest;
import com.javaproject.Backend.dto.response.ReportResponse;
import com.javaproject.Backend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service trả báo cáo phân tích chi tiêu
 * Không cần entity, trực tiếp trả ReportResponse
 */
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    // Nếu muốn gọi repository, inject ở đây (expenseRepository...)
    // private final ExpenseRepository expenseRepository;

    @Override
    public ReportResponse generateMonthlyReport(Long userId) {
        // Ví dụ tính toán tạm thời, thực tế có thể query DB
        BigDecimal totalExpense = BigDecimal.valueOf(1200.50);

        Map<String, BigDecimal> expenseByCategory = Map.of(
                "Food", BigDecimal.valueOf(500),
                "Transport", BigDecimal.valueOf(300),
                "Entertainment", BigDecimal.valueOf(400.5)
        );

        String advice = "Bạn nên giảm chi tiêu vào mục Entertainment.";

        ReportResponse response = new ReportResponse();
        response.setTotalExpense(totalExpense);
        response.setExpenseByCategory(expenseByCategory);
        response.setAdvice(advice);

        return response;
    }

    @Override
    public ReportResponse generateReport(ReportRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateReport'");
    }
}
