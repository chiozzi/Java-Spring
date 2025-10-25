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
    
    // busca todas as tags e converte para DTOs (response)
    public List<TagResponse> getAllTags() {
        return repository.findAll()
            .stream()
            .map(TagMapper::toResponse)
            .toList();
    }
    
    // busca tag por id e converte para DTO (response)
    public TagResponse getTagById(Long id) {
        Tag tag = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tag não encontrada"));
        return TagMapper.toResponse(tag);
    }
    
    // cria nova tag a partir do DTO (request) e converte para DTO (response)
    public TagResponse createTag(TagRequest request) {
        Tag tag = TagMapper.toEntity(request);
        tag = repository.save(tag);
        return TagMapper.toResponse(tag);
    }
    
    // atualiza tag existente a partir do DTO (request) e converte para DTO (response)
    public TagResponse updateTag(Long id, TagRequest request) {
        Tag tag = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tag não encontrada"));
        tag.setName(request.name());
        tag = repository.save(tag);
        return TagMapper.toResponse(tag);
    }
    
    // deleta tag por id
    public void deleteTag(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Tag não encontrada");
        }
        repository.deleteById(id);
    }
}