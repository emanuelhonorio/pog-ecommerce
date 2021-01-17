package com.emanuelhonorio.pogecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuelhonorio.pogecommerce.model.Tamanho;

public interface TamanhoRepository extends JpaRepository<Tamanho, Long>{
	List<Tamanho> findAllByProdutoId(Long id);
}
