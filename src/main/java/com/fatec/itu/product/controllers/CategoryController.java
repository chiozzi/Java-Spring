package com.fatec.itu.product.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.itu.product.dtos.CategoryRequest;
import com.fatec.itu.product.dtos.CategoryResponse;
import com.fatec.itu.product.dtos.ProductResponse;
import com.fatec.itu.product.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;


    
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        CategoryResponse category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }
    
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse category = categoryService.createCategory(request);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(category.id())
            .toUri();
        return ResponseEntity.created(location).body(category);
    }
    
    @GetMapping("{categoryId}/products")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable long categoryId) {
        return ResponseEntity.ok(categoryService.getProductsFromCategory(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        CategoryResponse category = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(category);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}