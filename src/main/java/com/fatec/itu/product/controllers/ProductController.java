package com.fatec.itu.product.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.itu.product.entities.Product;
import com.fatec.itu.product.repositories.ProductRepository;
import com.fatec.itu.product.services.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
 
    @Autowired
    private ProductService service;
    
    @GetMapping
    public List<Product> getProducts(){
        return service.getAllProducts();
    }

    //localhost:8080/products/2
    @GetMapping("{id}")
    public Product getProductById(@PathVariable long id)
    {
        return service.getProductById(id);
    }

}
