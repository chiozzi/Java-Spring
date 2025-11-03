package com.fatec.itu.product.dtos;

import java.util.Set;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        String name,

        @Min(value = 0, message = "O preço deve ser maior ou igual a zero")
        double price,
        
        // significa que a categoria é opcional
        Long categoryId,

        // significa que as tags são opcionais
        // set = coleção que não permite elementos duplicados
        Set<Long> tagIds
) {}

// recebe os dados enviados pelo usuário para criar ou atualizar um produto.
// campos:
//   - name: nome do produto
//   - price: preço do produto
//   - categoryId: id da categoria a que o produto pertence
//   - tagIds: conjunto de ids das tags associadas
// validações:
//   - name → obrigatório, entre 3 e 100 caracteres
//   - price → deve ser >= 0
// exemplo de uso:
//   enviado no corpo do POST ou PUT para /products

