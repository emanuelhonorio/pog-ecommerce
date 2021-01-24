package com.emanuelhonorio.pogecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuelhonorio.pogecommerce.model.Endereco;
import com.emanuelhonorio.pogecommerce.model.Usuario;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	List<Endereco> findByUsuario(Usuario usuario);
}
