package com.emanuelhonorio.pogecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuelhonorio.pogecommerce.model.Foto;
import com.emanuelhonorio.pogecommerce.model.Produto;

public interface FotoRepository extends JpaRepository<Foto, Long> {
	public void deleteByProduto(Produto produto);
}
