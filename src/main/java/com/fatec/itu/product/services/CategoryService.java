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

    
    // busca todas as categorias e converte para DTOs (response)
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
            .stream()
            .map(CategoryMapper::toResponse)
            .toList();
    }
    
    // busca categoria por id e converte para DTO (response)
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        return CategoryMapper.toResponse(category);
    }
    
    // cria nova categoria a partir do DTO (request) e converte para DTO (response)
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = CategoryMapper.toEntity(request);
        category = categoryRepository.save(category);
        return CategoryMapper.toResponse(category);
    }
    
    // atualiza categoria existente a partir do DTO (request) e converte para DTO (response)
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        category.setName(request.name());
        category = categoryRepository.save(category);
        return CategoryMapper.toResponse(category);
    }
    
    // deleta categoria por id
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada");
        }
        categoryRepository.deleteById(id);
    }

    // busca produtos de uma categoria por id da categoria e converte para DTOs (response)
    public List<ProductResponse> getProductsFromCategory(long id) {
        return productRepository.findByCategoryId(id)
            .stream()
            .map(ProductMapper::toDTO)
            .toList();
    }
}