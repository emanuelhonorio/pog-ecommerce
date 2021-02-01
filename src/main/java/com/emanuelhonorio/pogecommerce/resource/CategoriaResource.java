package com.emanuelhonorio.pogecommerce.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.pogecommerce.dto.CategoriaDTO;
import com.emanuelhonorio.pogecommerce.model.Categoria;
import com.emanuelhonorio.pogecommerce.model.Produto;
import com.emanuelhonorio.pogecommerce.repository.CategoriaRepository;
import com.emanuelhonorio.pogecommerce.repository.ProdutoRepository;
import com.emanuelhonorio.pogecommerce.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaServie;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Categoria> listCategorias(String nome) {
		if (nome != null) {
			return categoriaRepository.findAllByNomeIgnoreCaseContaining(nome);
		}
		return categoriaRepository.findAll();
	}

	@PostMapping
	public Categoria saveCategoria(@RequestBody @Valid CategoriaDTO categoriaDTO) {
		return categoriaServie.create(categoriaDTO);
	}

	@PutMapping("/{categoriaId}")
	public Categoria updateCategoria(@PathVariable Long categoriaId, @RequestBody @Valid CategoriaDTO categoriaDTO) {
		return categoriaServie.update(categoriaId, categoriaDTO);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		categoriaRepository.delete(new Categoria(id));
	}

	@GetMapping("/{categoriaId}/produtos")
	public Page<Produto> listProdutosByCategoria(@PathVariable Long categoriaId, Pageable pageable) {
		return produtoRepository.findAllByCategorias_Id(categoriaId, pageable);
	}
}
