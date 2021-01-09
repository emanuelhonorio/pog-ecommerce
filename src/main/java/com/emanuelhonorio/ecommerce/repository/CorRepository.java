package com.emanuelhonorio.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuelhonorio.ecommerce.model.Cor;

public interface CorRepository extends JpaRepository<Cor, Long>{
	List<Cor> findAllByProdutoId(Long id);
}
