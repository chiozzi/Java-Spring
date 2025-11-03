package com.fatec.itu.product.mappers;

import java.util.stream.Collectors;

import com.fatec.itu.product.dtos.ProductRequest;
import com.fatec.itu.product.dtos.ProductResponse;
import com.fatec.itu.product.entities.Product;


public class ProductMapper {

    // converte ProductRequest(dados vindo do cliente) para Product(entidade do banco)
    // usada normalemente no metodo POST e PUT
    public static Product toEntity(ProductRequest request) {
        Product p = new Product();
        p.setName(request.name());
        p.setPrice(request.price());
        return p;
    }

    // converte Product(entidade do banco) para ProductResponse(dados que vao para o cliente)
    // usada normalemente no metodo GET e no retorno do POST e PUT
    public static ProductResponse toDTO(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getCategory() != null ? CategoryMapper.toResponse(product.getCategory()) : null, // trata categoria nula
            product.getTags().stream().map(TagMapper::toResponse).collect(Collectors.toSet()) // converte cada Tag para TagResponse
        );
    }
}
