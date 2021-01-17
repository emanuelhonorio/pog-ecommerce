package com.emanuelhonorio.pogecommerce.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompraDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1)
	private Set<@Valid ItemCompraDTO> itens = new HashSet<>();

	public Set<ItemCompraDTO> getItens() {
		return itens;
	}

	public void setItens(Set<ItemCompraDTO> itens) {
		this.itens = itens;
	}

}
