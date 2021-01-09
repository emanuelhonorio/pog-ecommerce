package com.emanuelhonorio.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuelhonorio.ecommerce.model.Cor;
import com.emanuelhonorio.ecommerce.model.Tamanho;
import com.emanuelhonorio.ecommerce.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
	Optional<Estoque> findByCorAndTamanho(Cor cor, Tamanho tamanho);

}
