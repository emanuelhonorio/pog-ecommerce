package com.emanuelhonorio.pogecommerce.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.emanuelhonorio.pogecommerce.model.enums.TipoPagamentoEnum;

public class CompraDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1)
	private Set<@Valid ItemCompraDTO> itens = new HashSet<>();

	@NotNull
	@Positive
	private Long enderecoId;

	@NotNull
	private TipoPagamentoEnum tipoPagamento;

	@Positive
	private BigDecimal trocoPara;

	public Set<ItemCompraDTO> getItens() {
		return itens;
	}

	public void setItens(Set<ItemCompraDTO> itens) {
		this.itens = itens;
	}

	public Long getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Long enderecoId) {
		this.enderecoId = enderecoId;
	}

	public TipoPagamentoEnum getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamentoEnum tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public BigDecimal getTrocoPara() {
		return trocoPara;
	}

	public void setTrocoPara(BigDecimal trocoPara) {
		this.trocoPara = trocoPara;
	}

}
