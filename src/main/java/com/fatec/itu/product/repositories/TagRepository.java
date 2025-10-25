package com.fatec.itu.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.itu.product.entities.Tag;



@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}

// faz a comunicação entre o código e o banco de dados
// responsável por salvar, buscar, atualizar e deletar no banco
// conecta a entidade Tag com o banco de dados
// JpaRepository = CRUD pronto
