package com.emanuelhonorio.pogecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.emanuelhonorio.pogecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, QuerydslPredicateExecutor<Produto> {

	Page<Produto> findAllByCategorias_Id(Long id, Pageable pageable);

}
