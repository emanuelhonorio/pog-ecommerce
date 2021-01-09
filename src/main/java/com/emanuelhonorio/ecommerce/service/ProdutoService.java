package com.emanuelhonorio.ecommerce.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.emanuelhonorio.ecommerce.dto.CreateProdutoDTO;
import com.emanuelhonorio.ecommerce.error.exceptions.ResourceNotFoundException;
import com.emanuelhonorio.ecommerce.model.Categoria;
import com.emanuelhonorio.ecommerce.model.Foto;
import com.emanuelhonorio.ecommerce.model.Produto;
import com.emanuelhonorio.ecommerce.model.QProduto;
import com.emanuelhonorio.ecommerce.repository.ProdutoRepository;
import com.emanuelhonorio.ecommerce.service.filter.ProdutoFilter;
import com.querydsl.core.BooleanBuilder;

@Service
@Transactional
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaService categoriaService;

	public Produto findByIdOrThrow(Long id) {
		Optional<Produto> p = produtoRepository.findById(id);
		return p.orElseThrow(() -> new ResourceNotFoundException("Produto not found with id " + id));
	}

	public Page<Produto> filtrar(ProdutoFilter produtoFilter, int page, int size) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		
		if (!ObjectUtils.isEmpty(produtoFilter.getNome())) {
			booleanBuilder.and(QProduto.produto.nome.like("%"+produtoFilter.getNome()+"%"));
		}
		
		if (produtoFilter.getValorDe() != null) {
			
		}
		
		if (produtoFilter.getValorAte() != null) {
			
		}
		
		if (!produtoFilter.getCategorias().isEmpty()) {
			Set<Categoria> categorias = new HashSet<>();
			produtoFilter.getCategorias().forEach(cId -> categorias.add(new Categoria(cId)));
			booleanBuilder.and(QProduto.produto.categorias.any().in(categorias));
		}
		
		return produtoRepository.findAll(booleanBuilder.getValue(), PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));	
	}

	public Produto save(CreateProdutoDTO produtoDTO) {
		Produto newProduto = new Produto();

		// if he wants to update
		if (produtoDTO.getId() != null) {
			newProduto = findByIdOrThrow(produtoDTO.getId());
		}

		newProduto.setId(produtoDTO.getId());
		newProduto.setNome(produtoDTO.getNome());
		newProduto.setDescricao(produtoDTO.getDescricao());
		newProduto.setMarca(produtoDTO.getMarca());
		newProduto.setModelo(produtoDTO.getModelo());
		newProduto.setPeso(produtoDTO.getPeso());
		newProduto.setValorBase(produtoDTO.getValorBase());

		// set categorias
		Set<Categoria> categorias = new HashSet<>();
		produtoDTO.getCategorias().forEach(categoriaId -> {
			Categoria cat = categoriaService.findByIdOrThrow(categoriaId);
			categorias.add(cat);
		});
		newProduto.setCategorias(categorias);

		// set fotos
		Set<Foto> fotos = new HashSet<>();
		produtoDTO.getFotos().forEach(imageUrl -> {
			fotos.add(new Foto(imageUrl));
		});
		newProduto.setFotos(fotos);

		return produtoRepository.save(newProduto);
	}
}
