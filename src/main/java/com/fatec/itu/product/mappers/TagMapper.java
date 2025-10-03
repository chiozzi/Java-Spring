package com.fatec.itu.product.mappers;

import com.fatec.itu.product.dtos.TagRequest;
import com.fatec.itu.product.dtos.TagResponse;
import com.fatec.itu.product.entities.Tag;

;

public class TagMapper {
    
    public static Tag toEntity(TagRequest request) {
        Tag tag = new Tag();
        tag.setName(request.name());
        return tag;
    }
    
    public static TagResponse toResponse(Tag tag) {
        return new TagResponse(
            tag.getId(),
            tag.getName()
        );
    }
}