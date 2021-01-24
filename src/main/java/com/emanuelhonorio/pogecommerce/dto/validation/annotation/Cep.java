package com.emanuelhonorio.pogecommerce.dto.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.emanuelhonorio.pogecommerce.dto.validation.validator.CepValidator;

@Constraint(validatedBy = CepValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cep {
  
  String message() default "must be valid";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };

}
