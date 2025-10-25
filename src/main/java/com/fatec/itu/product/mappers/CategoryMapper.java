package com.fatec.itu.product.mappers;

import com.fatec.itu.product.dtos.CategoryRequest;
import com.fatec.itu.product.dtos.CategoryResponse;
import com.fatec.itu.product.entities.Category;

public class CategoryMapper {
    

    // converte CategoryRequest(dados vindo do cliente) para Category(entidade do banco)
    // usada normalemente no metodo POST e PUT
    public static Category toEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        return category;
    }
    
    // converte Category(entidade do banco) para CategoryResponse(dados que vao para o cliente)
    // usada normalemente no metodo GET e no retorno do POST e PUT
    public static CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
            category.getId(),
            category.getName()
        );
    }
}