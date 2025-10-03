package com.fatec.itu.product.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.itu.product.dtos.TagRequest;
import com.fatec.itu.product.dtos.TagResponse;
import com.fatec.itu.product.entities.Tag;
import com.fatec.itu.product.mappers.TagMapper;
import com.fatec.itu.product.repositories.TagRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TagService {
    
    @Autowired
    private TagRepository repository;
    
    public List<TagResponse> getAllTags() {
        return repository.findAll()
            .stream()
            .map(TagMapper::toResponse)
            .toList();
    }
    
    public TagResponse getTagById(Long id) {
        Tag tag = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tag não encontrada"));
        return TagMapper.toResponse(tag);
    }
    
    public TagResponse createTag(TagRequest request) {
        Tag tag = TagMapper.toEntity(request);
        tag = repository.save(tag);
        return TagMapper.toResponse(tag);
    }
    
    public TagResponse updateTag(Long id, TagRequest request) {
        Tag tag = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tag não encontrada"));
        tag.setName(request.name());
        tag = repository.save(tag);
        return TagMapper.toResponse(tag);
    }
    
    public void deleteTag(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Tag não encontrada");
        }
        repository.deleteById(id);
    }
}