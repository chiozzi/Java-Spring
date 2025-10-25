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


    // get normal
    // http://localhost:8080/categories
    // resultado: ele mostra a lista de todas categorias
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    
    // get pelo id
    // http://localhost:8080/categories/1
    // resultado: ele mostra a categoria com id 1 (ou a que vc colocar dps da /)
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        CategoryResponse category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }
    
    // cria e posta uma nova categoria
    // http://localhost:8080/categories
    /*
    {
        "name": "Smartphone"
    }
    */
    // resultado: ele salva e posta a categoria e mostra o que foi salvo
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

    // get produtos pela categoria
    // http://localhost:8080/categories/1/products
    // resultado: ele mostra a lista de produtos que pertencem a categoria com id 1 (ou a que vc colocar dps da /)
    @GetMapping("{categoryId}/products")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable long categoryId) {
        return ResponseEntity.ok(categoryService.getProductsFromCategory(categoryId));
    }

    // atualizar
    // http://localhost:8080/categories/3
    /*
    {
        "name": "Gadgets"
    }
    */  
    // resultado: ele atualiza a categoria com o id especificado e mostra o que foi atualizado (ou a que vc colocar dps da /)
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        CategoryResponse category = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(category);
    }
    
    // deletar
    // localhost:8080/categories/3
    // resultado: ele deleta a categoria com o id especificado (ou a que vc colocar dps da /)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}


// GET (id) - Buscar todas as categorias
// GET - Buscar categoria por ID
// POST - Criar categoria
// PUT - Atualizar categoria
// DELETE - Deletar categoria
