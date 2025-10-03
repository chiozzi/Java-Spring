package com.fatec.itu.product.mappers;

import com.fatec.itu.product.dtos.CategoryRequest;
import com.fatec.itu.product.dtos.CategoryResponse;
import com.fatec.itu.product.entities.Category;

public class CategoryMapper {
    
    public static Category toEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        return category;
    }
    
    public static CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
            category.getId(),
            category.getName()
        );
    }
}