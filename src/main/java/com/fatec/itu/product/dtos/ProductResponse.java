package com.fatec.itu.product.dtos;

public record ProductResponse(
        Long id,
        String name,
        Double price) {
}