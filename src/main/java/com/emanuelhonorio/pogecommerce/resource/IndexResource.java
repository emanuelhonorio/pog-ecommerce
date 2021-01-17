package com.emanuelhonorio.pogecommerce.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexResource {

	@GetMapping
	public String index() {
		return "The application is online";
	}
	
}
