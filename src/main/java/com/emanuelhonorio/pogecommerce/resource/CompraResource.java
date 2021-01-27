package com.emanuelhonorio.pogecommerce.resource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.pogecommerce.model.Compra;
import com.emanuelhonorio.pogecommerce.model.enums.StatusCompra;
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
	
	@PutMapping("/{id}/status")
	public void atualizarStatus(@PathVariable Long id, @RequestBody @NotNull @Valid StatusCompra status) {
		this.compraService.atualizarStatus(id, status);
	}
	
	@DeleteMapping("/{id}")
	public void atualizarStatus(@PathVariable Long id) {
		this.compraService.safeDelete(id);
	}
}
