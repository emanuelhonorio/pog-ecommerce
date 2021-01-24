package com.emanuelhonorio.pogecommerce.dto.validation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.emanuelhonorio.pogecommerce.dto.validation.annotation.Cep;

public class CepValidator implements ConstraintValidator<Cep, String> {

	private Pattern pattern = Pattern.compile("[0-9]{5}[0-9]{3}");

	@Override
	public void initialize(Cep constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Matcher m = pattern.matcher(value);
		return m.matches();
	}

}