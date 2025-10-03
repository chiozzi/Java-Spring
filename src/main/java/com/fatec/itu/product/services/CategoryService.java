package com.fatec.itu.product.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.itu.product.dtos.CategoryRequest;
import com.fatec.itu.product.dtos.CategoryResponse;
import com.fatec.itu.product.dtos.ProductResponse;
import com.fatec.itu.product.entities.Category;
import com.fatec.itu.product.mappers.CategoryMapper;
import com.fatec.itu.product.mappers.ProductMapper;
import com.fatec.itu.product.repositories.CategoryRepository;
import com.fatec.itu.product.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
            .stream()
            .map(CategoryMapper::toResponse)
            .toList();
    }
    
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        return CategoryMapper.toResponse(category);
    }
    
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = CategoryMapper.toEntity(request);
        category = categoryRepository.save(category);
        return CategoryMapper.toResponse(category);
    }
    
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        category.setName(request.name());
        category = categoryRepository.save(category);
        return CategoryMapper.toResponse(category);
    }
    
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada");
        }
        categoryRepository.deleteById(id);
    }

    public List<ProductResponse> getProductsFromCategory(long id) {
        return productRepository.findByCategoryId(id)
            .stream()
            .map(ProductMapper::toDTO)
            .toList();
    }
}