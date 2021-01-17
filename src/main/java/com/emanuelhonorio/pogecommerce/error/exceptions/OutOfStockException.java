package com.emanuelhonorio.pogecommerce.error.exceptions;

public class OutOfStockException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OutOfStockException(String msg) {
		super(msg);
	}

}
