package com.emanuelhonorio.pogecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuelhonorio.pogecommerce.model.Cor;
import com.emanuelhonorio.pogecommerce.model.Estoque;
import com.emanuelhonorio.pogecommerce.model.Tamanho;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
	Optional<Estoque> findByCorAndTamanho(Cor cor, Tamanho tamanho);
	Optional<Estoque> findByCorNomeAndTamanhoNome(String cor, String tamanho);

}
