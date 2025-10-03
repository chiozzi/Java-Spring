package com.fatec.itu.product.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.itu.product.dtos.TagRequest;
import com.fatec.itu.product.dtos.TagResponse;
import com.fatec.itu.product.services.TagService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tags")
@CrossOrigin
public class TagController {
    
    @Autowired
    private TagService service;
    
    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<TagResponse> tags = service.getAllTags();
        return ResponseEntity.ok(tags);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> getTagById(@PathVariable Long id) {
        TagResponse tag = service.getTagById(id);
        return ResponseEntity.ok(tag);
    }
    
    @PostMapping
    public ResponseEntity<TagResponse> createTag(@Valid @RequestBody TagRequest request) {
        TagResponse tag = service.createTag(request);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(tag.id())
            .toUri();
        return ResponseEntity.created(location).body(tag);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TagResponse> updateTag(@PathVariable Long id, @Valid @RequestBody TagRequest request) {
        TagResponse tag = service.updateTag(id, request);
        return ResponseEntity.ok(tag);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        service.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}