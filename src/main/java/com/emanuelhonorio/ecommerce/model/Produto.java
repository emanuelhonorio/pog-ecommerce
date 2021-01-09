package com.emanuelhonorio.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String descricao;

	private String marca;

	private String modelo;

	private Double peso;

	private BigDecimal valorBase;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "produtos_categorias", joinColumns = { @JoinColumn(name = "produto_id") }, inverseJoinColumns = {
			@JoinColumn(name = "categoria_id") })
	private Set<Categoria> categorias = new HashSet<>();

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "produto")
	private Set<Estoque> estoques = new HashSet<>();

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "produto")
	private Set<Foto> fotos = new HashSet<>();

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "produto")
	private Set<Cor> cores = new HashSet<>();

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "produto")
	private Set<Tamanho> tamanhos = new HashSet<>();

	public Produto() {
		super();
	}

	public Produto(Long id) {
		super();
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

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

	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Set<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(Set<Estoque> estoques) {
		this.estoques = estoques;
	}

	public Set<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(Set<Foto> fotos) {
		this.fotos = fotos;
	}

	public Set<Cor> getCores() {
		return cores;
	}

	public void setCores(Set<Cor> cores) {
		this.cores = cores;
	}

	public Set<Tamanho> getTamanhos() {
		return tamanhos;
	}

	public void setTamanhos(Set<Tamanho> tamanhos) {
		this.tamanhos = tamanhos;
	}

}
