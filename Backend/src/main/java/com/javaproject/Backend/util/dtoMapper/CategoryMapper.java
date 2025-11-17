package com.javaproject.Backend.util.dtoMapper;

import com.javaproject.Backend.domain.Category;
import com.javaproject.Backend.dto.request.CategoryRequest;
import com.javaproject.Backend.dto.response.CategoryResponse;

public class CategoryMapper {

    // Chuyển Category entity sang CategoryResponse DTO
    public static CategoryResponse toDto(Category c) {
        CategoryResponse dto = new CategoryResponse();  // Tạo DTO rỗng

        dto.setCategoryId(c.getCategoryId());          // ID danh mục
        dto.setUserId(c.getUser().getUserId());        // ID user sở hữu danh mục
        dto.setName(c.getName());                       // Tên danh mục
        dto.setType(c.getType());                       // Loại danh mục (expense / income)

        return dto;                                    // Trả DTO cho controller
    }
    /**
     * DTO → Entity
     */
    public static Category toEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setType(request.getType());
        return category;
    }

    /**
     * Cập nhật entity từ request
     */
    public static void updateFromRequest(Category category, CategoryRequest request) {
        category.setName(request.getName());
        category.setType(request.getType());
    }
}
