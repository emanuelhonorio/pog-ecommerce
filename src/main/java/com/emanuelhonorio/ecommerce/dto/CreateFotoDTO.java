package com.emanuelhonorio.ecommerce.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

public class CreateFotoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@URL
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
