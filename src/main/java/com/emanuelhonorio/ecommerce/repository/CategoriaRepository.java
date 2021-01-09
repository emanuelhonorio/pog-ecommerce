package com.emanuelhonorio.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuelhonorio.ecommerce.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
