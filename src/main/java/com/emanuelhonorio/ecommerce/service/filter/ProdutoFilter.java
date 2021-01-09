package com.emanuelhonorio.ecommerce.service.filter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProdutoFilter implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private Set<Long> categorias = new HashSet<>();
	private BigDecimal valorDe;
	private BigDecimal valorAte;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Long> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Long> categorias) {
		this.categorias = categorias;
	}

	public BigDecimal getValorDe() {
		return valorDe;
	}

	public void setValorDe(BigDecimal valorDe) {
		this.valorDe = valorDe;
	}

	public BigDecimal getValorAte() {
		return valorAte;
	}

	public void setValorAte(BigDecimal valorAte) {
		this.valorAte = valorAte;
	}

}
