package com.emanuelhonorio.pogecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/gen")
public class PasswordEncoderGenerator {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping
	public String gen() {
		return encoder.encode("4815162342");
	}
}
