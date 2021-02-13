package com.emanuelhonorio.pogecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.emanuelhonorio.pogecommerce.config.property.PogApiProperties;

@SpringBootApplication
@EnableConfigurationProperties(PogApiProperties.class)
public class PogEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PogEcommerceApplication.class, args);
	}

}
