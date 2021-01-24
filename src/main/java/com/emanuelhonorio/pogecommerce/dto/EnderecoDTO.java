package com.emanuelhonorio.pogecommerce.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.emanuelhonorio.pogecommerce.dto.validation.annotation.Cep;

public class EnderecoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Positive
	private Long id;

	@Cep
	@NotBlank
	private String cep;

	private String complemento;

	@NotBlank
	private String numero;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
