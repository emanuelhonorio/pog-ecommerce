package com.emanuelhonorio.ecommerce.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.ecommerce.dto.CreateProdutoDTO;
import com.emanuelhonorio.ecommerce.model.Produto;
import com.emanuelhonorio.ecommerce.repository.ProdutoRepository;
import com.emanuelhonorio.ecommerce.service.ProdutoService;
import com.emanuelhonorio.ecommerce.service.filter.ProdutoFilter;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public Page<Produto> listarProdutos(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "500") int size, ProdutoFilter produtoFilter) {
		Page<Produto> produtosPage = produtoService.filtrar(produtoFilter, page, size);
		return produtosPage;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> findProutoById(@PathVariable Long id) {
		Produto produto = produtoService.findByIdOrThrow(id);
		return ResponseEntity.ok().body(produto);
	}

	@PostMapping
	public Produto saveProduto(@RequestBody @Valid CreateProdutoDTO produtoDTO) {
		produtoDTO.setId(null);
		return produtoService.save(produtoDTO);
	}

	@PutMapping("/{id}")
	public Produto updateProduto(@PathVariable Long id, @RequestBody @Valid CreateProdutoDTO produtoDTO) {
		produtoDTO.setId(id);
		return produtoService.save(produtoDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
		Produto p = new Produto();
		p.setId(id);
		produtoRepository.delete(p);

		return ResponseEntity.noContent().build();
	}
}
