package com.fatec.itu.product.dtos;

import java.util.Set;

public record ProductResponse(
        Long id,
        String name,
        Double price,
        CategoryResponse category,
        Set<TagResponse> tags
) {}


// envia os dados de um produto como resposta ao cliente
// campos:
//   - id: identificador único do produto
//   - name: nome do produto
//   - price: preço do produto
//   - category: categoria associada ao produto (CategoryResponse)
//   - tags: conjunto de tags associadas ao produto (Set<TagResponse>)
// exemplo de uso:
//   retornado no corpo da resposta de GET /products ou GET /products/{id}