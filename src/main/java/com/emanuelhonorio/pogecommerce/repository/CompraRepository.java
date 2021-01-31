package com.emanuelhonorio.pogecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.emanuelhonorio.pogecommerce.model.Compra;
import com.emanuelhonorio.pogecommerce.model.Usuario;

public interface CompraRepository extends JpaRepository<Compra, Long>, QuerydslPredicateExecutor<Compra> {
	List<Compra> findByUsuario(Usuario usuario);
}
