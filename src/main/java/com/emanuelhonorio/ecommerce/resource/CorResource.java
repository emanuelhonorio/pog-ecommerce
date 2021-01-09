package com.emanuelhonorio.ecommerce.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.ecommerce.dto.CreateCorDTO;
import com.emanuelhonorio.ecommerce.model.Cor;
import com.emanuelhonorio.ecommerce.model.Produto;
import com.emanuelhonorio.ecommerce.repository.CorRepository;
import com.emanuelhonorio.ecommerce.service.ProdutoService;

@RestController
public class CorResource {

	@Autowired
	private CorRepository corRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping("/produtos/{produtoId}/cores")
	public ResponseEntity<?> create(@PathVariable Long produtoId, @RequestBody @Valid CreateCorDTO corDTO) {		
		Produto produto = produtoService.findByIdOrThrow(produtoId);
		
		Cor newCor = new Cor();
		newCor.setNome(corDTO.getNome());
		newCor.setProduto(produto);
		
		corRepository.save(newCor);			
		return ResponseEntity.noContent().build();		
	}
	
	@GetMapping("/produtos/{produtoId}/cores")
	public ResponseEntity<?> create(@PathVariable Long produtoId) {		
		List<Cor> cores = corRepository.findAllByProdutoId(produtoId);
		return ResponseEntity.ok().body(cores);
	}
	
}
