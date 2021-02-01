package com.emanuelhonorio.pogecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuelhonorio.pogecommerce.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	List<Categoria> findAllByNomeIgnoreCaseContaining(String nome);
}
