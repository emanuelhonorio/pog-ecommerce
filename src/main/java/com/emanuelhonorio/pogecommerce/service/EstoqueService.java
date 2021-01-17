package com.emanuelhonorio.pogecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanuelhonorio.pogecommerce.dto.CreateEstoqueDTO;
import com.emanuelhonorio.pogecommerce.error.exceptions.ResourceAlreadyExistsException;
import com.emanuelhonorio.pogecommerce.error.exceptions.ResourceNotFoundException;
import com.emanuelhonorio.pogecommerce.model.Cor;
import com.emanuelhonorio.pogecommerce.model.Estoque;
import com.emanuelhonorio.pogecommerce.model.Produto;
import com.emanuelhonorio.pogecommerce.model.Tamanho;
import com.emanuelhonorio.pogecommerce.repository.CorRepository;
import com.emanuelhonorio.pogecommerce.repository.EstoqueRepository;
import com.emanuelhonorio.pogecommerce.repository.TamanhoRepository;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CorRepository corRepository;

	@Autowired
	private TamanhoRepository tamanhoRepository;

	public Estoque findByIdOrThrow(Long id) {
		Optional<Estoque> estoqueOpt = estoqueRepository.findById(id);
		return estoqueOpt.orElseThrow(() -> new ResourceNotFoundException("Stock not found with id " + id));
	}

	public Estoque save(CreateEstoqueDTO estoqueDTO) {
		Estoque newEstoque = new Estoque();

		// if he wants to update
		if (estoqueDTO.getId() != null) {
			newEstoque = findByIdOrThrow(estoqueDTO.getId());
		}

		Produto produto = produtoService.findByIdOrThrow(estoqueDTO.getProdutoId());
		Tamanho tamanho = new Tamanho(estoqueDTO.getTamanhoId());
		Cor cor = new Cor(estoqueDTO.getCorId());

		Optional<Estoque> estoqueExistente = estoqueRepository.findByCorAndTamanho(cor, tamanho);

		// check if a same variant other than it self exists
		if (estoqueExistente.isPresent() && !estoqueExistente.get().getId().equals(estoqueDTO.getId())) {
			throw new ResourceAlreadyExistsException("Stock already exists");
		}

		// if he pass a cor, it has to be a valid cor
		if (cor.getId() != null && corRepository.findById(cor.getId()).isEmpty()) {
			throw new ResourceNotFoundException("Color not found with id " + cor.getId());
		}

		// if he pass a tamanho, it has to be a valid tamanho
		if (tamanho.getId() != null && tamanhoRepository.findById(tamanho.getId()).isEmpty()) {
			throw new ResourceNotFoundException("Size not found with id " + tamanho.getId());
		}

		newEstoque.setId(estoqueDTO.getId());
		newEstoque.setProduto(produto);
		newEstoque.setTamanho(tamanho);
		newEstoque.setCor(cor);
		newEstoque.setQtdEstoque(estoqueDTO.getQtdEstoque());
		newEstoque.setAcrescimoValor(estoqueDTO.getAcrescimoValor());

		return estoqueRepository.save(newEstoque);
	}

}
