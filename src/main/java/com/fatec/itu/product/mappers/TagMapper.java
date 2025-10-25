package com.fatec.itu.product.mappers;

import com.fatec.itu.product.dtos.TagRequest;
import com.fatec.itu.product.dtos.TagResponse;
import com.fatec.itu.product.entities.Tag;

;

public class TagMapper {
    
    // converte TagRequest(dados vindo do cliente) para Tag(entidade do banco)
    // usada normalemente no metodo POST e PUT
    public static Tag toEntity(TagRequest request) {
        Tag tag = new Tag();
        tag.setName(request.name());
        return tag;
    }
    
    // converte Tag(entidade do banco) para TagResponse(dados que vao para o cliente)
    // usada normalemente no metodo GET e no retorno do POST e PUT
    public static TagResponse toResponse(Tag tag) {
        return new TagResponse(
            tag.getId(),
            tag.getName()
        );
    }
}