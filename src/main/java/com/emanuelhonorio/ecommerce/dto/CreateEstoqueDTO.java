package com.emanuelhonorio.ecommerce.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class CreateEstoqueDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Positive
	private Long id;

	@NotNull
	@Positive
	private Long produtoId;

	@Positive
	private Long tamanhoId;

	@Positive
	private Long corId;

	@NotNull
	@PositiveOrZero
	private Long qtdEstoque;

	@NotNull
	private BigDecimal acrescimoValor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Long qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public BigDecimal getAcrescimoValor() {
		return acrescimoValor;
	}

	public void setAcrescimoValor(BigDecimal acrescimoValor) {
		this.acrescimoValor = acrescimoValor;
	}

}
