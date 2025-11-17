package com.javaproject.Backend.service;

import com.javaproject.Backend.dto.request.ReportRequest;
import com.javaproject.Backend.dto.response.ReportResponse;

/**
 * Interface định nghĩa các phương thức trả báo cáo chi tiêu
 */
public interface ReportService {

    // Trả báo cáo chi tiêu theo tháng cho user
    ReportResponse generateMonthlyReport(Long userId);

    ReportResponse generateReport(ReportRequest request);
}
