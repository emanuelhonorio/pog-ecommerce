package com.emanuelhonorio.pogecommerce.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

public class CreateProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Positive
	private Long id;

	@NotBlank
	private String nome;

	private String descricao;

	@NotBlank
	private String marca;

	private String modelo;

	private Double peso;

	@NotNull
	@Positive
	private BigDecimal valorBase;

	@NotNull
	@Size(min = 1)
	private Set<Long> categorias = new HashSet<>();

	@NotNull
	@Size(min = 1)
	private Set<@URL String> fotos = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Long> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Long> categorias) {
		this.categorias = categorias;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public BigDecimal getValorBase() {
		return valorBase;
	}

	public void setValorBase(BigDecimal valorBase) {
		this.valorBase = valorBase;
	}

	public Set<String> getFotos() {
		return fotos;
	}

	public void setFotos(Set<String> fotos) {
		this.fotos = fotos;
	}

}
