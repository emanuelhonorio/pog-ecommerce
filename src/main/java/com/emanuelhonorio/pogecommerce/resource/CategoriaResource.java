package com.emanuelhonorio.pogecommerce.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.pogecommerce.model.Categoria;
import com.emanuelhonorio.pogecommerce.model.Produto;
import com.emanuelhonorio.pogecommerce.repository.CategoriaRepository;
import com.emanuelhonorio.pogecommerce.repository.ProdutoRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Categoria> listCategorias() {
		return categoriaRepository.findAll();
	}
	
	@PostMapping
	public Categoria saveCategoria(@RequestBody @Valid Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	@GetMapping("/{categoriaId}/produtos")
	public Page<Produto> listProdutosByCategoria(@PathVariable Long categoriaId, Pageable pageable) {
		return produtoRepository.findAllByCategorias_Id(categoriaId, pageable);
	}
}
