package com.fatec.itu.product.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.itu.product.dtos.ProductRequest;
import com.fatec.itu.product.dtos.ProductResponse;
import com.fatec.itu.product.entities.Category;
import com.fatec.itu.product.entities.Product;
import com.fatec.itu.product.entities.Tag;
import com.fatec.itu.product.mappers.ProductMapper;
import com.fatec.itu.product.repositories.CategoryRepository;
import com.fatec.itu.product.repositories.ProductRepository;
import com.fatec.itu.product.repositories.TagRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private TagRepository tagRepository;

    public List<ProductResponse> getProducts() {
        return repository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    public ProductResponse getProductById(long id) {
        return repository.findById(id)
                .map(ProductMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Produto não cadastrado"));
    }

    public void deleteProductById(long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new EntityNotFoundException("Produto não existe");

    }

    public ProductResponse saveProduct(ProductRequest request) {
        Product product = ProductMapper.toEntity(request);
        
        if (request.categoryId() != null) {
            Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
            product.setCategory(category);
        }
        
        if (request.tagIds() != null && !request.tagIds().isEmpty()) {
            Set<Tag> tags = new HashSet<>();
            for (Long tagId : request.tagIds()) {
                Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new EntityNotFoundException("Tag não encontrada: " + tagId));
                tags.add(tag);
            }
            product.setTags(tags);
        }
        
        Product savedProduct = repository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    public void updateProduct(ProductRequest request, long id) {
        Product product = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        
        product.setName(request.name());
        product.setPrice(request.price());
        
        if (request.categoryId() != null) {
            Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
            product.setCategory(category);
        } else {
            product.setCategory(null);
        }
        
        if (request.tagIds() != null) {
            Set<Tag> tags = new HashSet<>();
            for (Long tagId : request.tagIds()) {
                Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new EntityNotFoundException("Tag não encontrada: " + tagId));
                tags.add(tag);
            }
            product.setTags(tags);
        } else {
            product.getTags().clear();
        }
        
        repository.save(product);
    }

    public List<ProductResponse> getProductsByCategory(long categoryId) {
        return repository.findByCategoryId(categoryId)
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

}
