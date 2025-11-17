package com.javaproject.Backend.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity // Đánh dấu class entity
@Table(name = "expenses", indexes = { //Chỉ  định tên bảng và tạo index
        @Index(name = "idx_expense_user", columnList = "user_id"), //Mục lục, giúp tìm kiếm theo idx nhanh hơn
        @Index(name = "idx_expense_category", columnList = "category_id"),
        @Index(name = "idx_expense_date", columnList = "expense_date")
})
public class Expense {
    // Đánh dấu là khóa chính trong JPA
    @Id 
    // Yêu cầu JPA/Hibernate để DB tự sinh giá trị (AUTO_INCREMENT / IDENTITY)
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "expense_id", nullable = false) // Đặt trường java expenseId thành cột db
    private Long expenseId;

    // optional = false = bắt buộc phải có user → không thể có expense “vô chủ”
    // fetch = LAZY = chỉ load user khi thật sự cần (tối ưu hiệu năng)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_expense_user"))
    private User user;
    // Tham chiếu giá trị để lưu vào user_id từ bảng user, không tạo cột riêng

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_expense_category"))
    private Category category;

    // precision số chữ số tối đa, scale số chữ số thập phân
    // @column là mô tả cấu trúc của cột database, có những gì, điều kiện gì. Nhưng nó không kiểm tra giá trị gán vào
    @Column(name = "amount", nullable = false, precision = 12, scale = 2) 
    private BigDecimal amount;

    // Kiểu text, có thể mô tả dài
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "expense_date", nullable = false)
    private LocalDate expenseDate;

    @Column(name = "created_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now(); // thời điểm khi expense được tạo

    // ===== Constructors =====
    public Expense() {
    }

    public Expense(User user, Category category, BigDecimal amount, String description, LocalDate expenseDate) {
        this.user = user;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.expenseDate = expenseDate;
    }

    // ===== Getters & Setters =====
    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
