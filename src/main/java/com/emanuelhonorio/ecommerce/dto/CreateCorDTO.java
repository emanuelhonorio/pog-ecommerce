package com.emanuelhonorio.ecommerce.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class CreateCorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
