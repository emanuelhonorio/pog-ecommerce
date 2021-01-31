package com.emanuelhonorio.pogecommerce.service.filter;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Positive;

import com.emanuelhonorio.pogecommerce.model.enums.StatusCompra;

public class CompraFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Positive
	private Long id;

	private StatusCompra status;

	private LocalDate data;

	private Boolean deleted;

	private Boolean entregue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusCompra getStatus() {
		return status;
	}

	public void setStatus(StatusCompra status) {
		this.status = status;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Boolean getEntregue() {
		return entregue;
	}

	public void setEntregue(Boolean entregue) {
		this.entregue = entregue;
	}

}
