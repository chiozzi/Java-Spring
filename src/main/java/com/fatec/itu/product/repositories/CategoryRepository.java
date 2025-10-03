package com.fatec.itu.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.itu.product.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}