package com.fatec.itu.product.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.itu.product.dtos.ProductRequest;
import com.fatec.itu.product.dtos.ProductResponse;
import com.fatec.itu.product.entities.Product;
import com.fatec.itu.product.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    // localhost:8080/products/2
    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable long id) {
        service.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@Valid @RequestBody ProductRequest request)
    {
        ProductResponse newProduct = service.saveProduct(request);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProduct.id())
                .toUri();
        return ResponseEntity.created(location)
                             .body(newProduct);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateProduct( @PathVariable long id,
                                               @Valid @RequestBody ProductRequest request
                                              )
    {
        service.updateProduct(request, id);
        return ResponseEntity.noContent().build();
    }


}
