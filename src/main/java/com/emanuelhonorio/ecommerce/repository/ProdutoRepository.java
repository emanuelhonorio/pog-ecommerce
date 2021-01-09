package com.emanuelhonorio.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.emanuelhonorio.ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, QuerydslPredicateExecutor<Produto> {

	boolean existsById(Long id);

	Page<Produto> findAllByCategorias_Id(Long id, Pageable pageable);

	Page<Produto> findAllByNomeIgnoreCaseContaining(String nome, Pageable pageable);
}