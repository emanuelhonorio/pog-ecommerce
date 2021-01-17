package com.emanuelhonorio.pogecommerce.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.pogecommerce.dto.CompraDTO;
import com.emanuelhonorio.pogecommerce.model.Compra;
import com.emanuelhonorio.pogecommerce.repository.CompraRepository;
import com.emanuelhonorio.pogecommerce.service.CompraService;

@RestController
@RequestMapping("/compras")
public class CompraResource {
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private CompraService compraService;
	
	@GetMapping
	public List<Compra> listar() {
		return compraRepository.findAll();
	}
	
	@PostMapping
	public Compra comprar(@RequestBody @Valid CompraDTO compraDTO) {
		return compraService.comprar(compraDTO);
	}
}
