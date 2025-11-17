package com.javaproject.Backend.service;

import com.javaproject.Backend.dto.request.CategoryRequest;
import com.javaproject.Backend.dto.response.CategoryResponse;
import java.util.List;

/**
 * Interface định nghĩa các phương thức CRUD cho Category
 */
public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);

    CategoryResponse updateCategory(Long categoryId, CategoryRequest request);

    void deleteCategory(Long categoryId);

    List<CategoryResponse> getCategoriesByUser(Long userId);
}
