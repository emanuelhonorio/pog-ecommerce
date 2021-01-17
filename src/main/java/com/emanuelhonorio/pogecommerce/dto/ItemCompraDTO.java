package com.emanuelhonorio.pogecommerce.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ItemCompraDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Positive
	private Long produtoId;

	@NotNull
	@Positive
	private Long corId;

	@NotNull
	@Positive
	private Long tamanhoId;

	@NotNull
	@Positive
	private Long quantidade;

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Long getCorId() {
		return corId;
	}

	public void setCorId(Long corId) {
		this.corId = corId;
	}

	public Long getTamanhoId() {
		return tamanhoId;
	}

	public void setTamanhoId(Long tamanhoId) {
		this.tamanhoId = tamanhoId;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}
