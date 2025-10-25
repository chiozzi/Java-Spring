package com.fatec.itu.product.dtos;

public record CategoryResponse(
    Long id,
    String name
) {}

// envia os dados de uma categoria como resposta ao cliente
// campos:
//   - id: identificador único da categoria
//   - name: nome da categoria
// exemplo de uso:
//   retornado no corpo da resposta de GET /categories ou GET /categories/{id}