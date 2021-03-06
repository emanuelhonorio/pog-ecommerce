package com.emanuelhonorio.pogecommerce.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.pogecommerce.dto.CreateTamanhoDTO;
import com.emanuelhonorio.pogecommerce.model.Produto;
import com.emanuelhonorio.pogecommerce.model.Tamanho;
import com.emanuelhonorio.pogecommerce.repository.TamanhoRepository;
import com.emanuelhonorio.pogecommerce.service.ProdutoService;

@RestController
public class TamanhoResource {

	@Autowired
	private TamanhoRepository tamanhoRepository;

	@Autowired
	private ProdutoService produtoService;

	@PostMapping("/produtos/{produtoId}/tamanhos")
	public ResponseEntity<?> create(@PathVariable Long produtoId, @RequestBody @Valid CreateTamanhoDTO tamanhoDTO) {
		Produto produto = produtoService.findByIdOrThrow(produtoId);

		Tamanho newTamanho = new Tamanho();
		newTamanho.setNome(tamanhoDTO.getNome());
		newTamanho.setProduto(produto);

		tamanhoRepository.save(newTamanho);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/produtos/{produtoId}/tamanhos")
	public ResponseEntity<?> create(@PathVariable Long produtoId) {
		List<Tamanho> tamanhos = tamanhoRepository.findAllByProdutoId(produtoId);
		return ResponseEntity.ok().body(tamanhos);
	}

}