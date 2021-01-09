package com.emanuelhonorio.ecommerce.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.ecommerce.dto.CreateEstoqueDTO;
import com.emanuelhonorio.ecommerce.model.Estoque;
import com.emanuelhonorio.ecommerce.repository.EstoqueRepository;
import com.emanuelhonorio.ecommerce.service.EstoqueService;

@RestController
@RequestMapping("/estoques")
public class EstoqueResource {

	@Autowired
	private EstoqueService estoqueService;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@PostMapping
	public ResponseEntity<Estoque> create(@RequestBody @Valid CreateEstoqueDTO estoqueDTO) {
		estoqueDTO.setId(null);
		Estoque newEstoque = estoqueService.save(estoqueDTO);
		return ResponseEntity.ok().body(newEstoque);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estoque> update(@PathVariable Long id, @RequestBody @Valid CreateEstoqueDTO estoqueDTO) {
		estoqueDTO.setId(id);
		Estoque newEstoque = estoqueService.save(estoqueDTO);
		return ResponseEntity.ok().body(newEstoque);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Estoque> delete(@PathVariable Long id) {
		estoqueRepository.delete(new Estoque(id));
		return ResponseEntity.noContent().build();
	}
}
