package com.fatec.itu.product.dtos;

import java.util.Set;

public record ProductResponse(
        Long id,
        String name,
        Double price,
        CategoryResponse category,
        Set<TagResponse> tags
) {}
