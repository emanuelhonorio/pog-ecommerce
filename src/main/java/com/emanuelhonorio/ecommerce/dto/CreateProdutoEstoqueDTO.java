package com.emanuelhonorio.ecommerce.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Deprecated
public class CreateProdutoEstoqueDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@PositiveOrZero
	private Long quantidadeEstoque;

	@NotNull
	@Positive
	private Long produtoId;

	@NotNull
	@Positive
	private Long tamanhoId;

	@NotNull
	@Positive
	private Long corId;

	@NotNull
	@PositiveOrZero
	private BigDecimal valor;

	public Long getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Long quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Long getTamanhoId() {
		return tamanhoId;
	}

	public void setTamanhoId(Long tamanhoId) {
		this.tamanhoId = tamanhoId;
	}

	public Long getCorId() {
		return corId;
	}

	public void setCorId(Long corId) {
		this.corId = corId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
