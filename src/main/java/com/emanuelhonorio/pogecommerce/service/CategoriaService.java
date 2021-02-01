package com.emanuelhonorio.pogecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanuelhonorio.pogecommerce.dto.CategoriaDTO;
import com.emanuelhonorio.pogecommerce.error.exceptions.ResourceNotFoundException;
import com.emanuelhonorio.pogecommerce.model.Categoria;
import com.emanuelhonorio.pogecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository  categoriaRepository;
	
	public Categoria findByIdOrThrow(Long id) {
		Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
		return categoriaOpt.orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));		
	}
	
	public Categoria create(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		
		categoria.setNome(categoriaDTO.getNome());
		return categoriaRepository.save(categoria);
	}
	
	public Categoria update(Long id, CategoriaDTO categoriaDTO) {
		Categoria categoria = findByIdOrThrow(id);
		
		categoria.setNome(categoriaDTO.getNome());
		return categoriaRepository.save(categoria);
	}
}
