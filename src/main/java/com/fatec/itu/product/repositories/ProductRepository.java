package com.fatec.itu.product.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.itu.product.entities.Product;



public interface ProductRepository extends JpaRepository<Product,Long>{
    
    // método personalizado para buscar produtos por categoria
    List<Product> findByCategoryId(Long categoryId);
    
}


// faz a comunicação entre o código e o banco de dados
// responsável por salvar, buscar, atualizar e deletar no banco
// conecta a entidade Product com o banco de dados
// JpaRepository = CRUD pronto
