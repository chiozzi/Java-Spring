package com.fatec.itu.product.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TagRequest(
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    String name
) {}


// recebe os dados enviados pelo usuário para criar ou atualizar uma tag
// campos:
//   - name: nome da tag
// validações:
//   - name → obrigatório, entre 2 e 50 caracteres
// exemplo de uso:
//   enviado no corpo do POST ou PUT para /tag
