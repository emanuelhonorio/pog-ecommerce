package com.emanuelhonorio.pogecommerce.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.pogecommerce.model.Compra;
import com.emanuelhonorio.pogecommerce.model.enums.StatusCompraEnum;
import com.emanuelhonorio.pogecommerce.service.CompraService;
import com.emanuelhonorio.pogecommerce.service.filter.CompraFilter;

@RestController
@RequestMapping("/compras")
public class CompraResource {

	@Autowired
	private CompraService compraService;

	@GetMapping
	public Page<Compra> listar(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "500") int size, CompraFilter compraFilter) {
		return compraService.filtrarAdmin(compraFilter, page, size);
	}

	@PutMapping("/{id}/status")
	public void atualizarStatus(@PathVariable Long id, @RequestBody @NotNull @Valid StatusCompraEnum status) {
		this.compraService.atualizarStatus(id, status);
	}

	@DeleteMapping("/{id}")
	public void atualizarStatus(@PathVariable Long id) {
		this.compraService.safeDelete(id);
	}
}
