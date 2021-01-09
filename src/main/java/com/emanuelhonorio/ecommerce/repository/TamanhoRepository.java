package com.emanuelhonorio.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuelhonorio.ecommerce.model.Tamanho;

public interface TamanhoRepository extends JpaRepository<Tamanho, Long>{
	List<Tamanho> findAllByProdutoId(Long id);
}
