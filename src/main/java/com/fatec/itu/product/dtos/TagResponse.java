package com.fatec.itu.product.dtos;

public record TagResponse(
    Long id,
    String name
) {}


// envia os dados de uma tag como resposta ao cliente
// campos:
//   - id: identificador único da tag
//   - name: nome da tag
// exemplo de uso:
//   retornado no corpo da resposta de GET /tags ou GET /tags/{id}