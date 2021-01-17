package com.emanuelhonorio.pogecommerce.service;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanuelhonorio.pogecommerce.dto.CompraDTO;
import com.emanuelhonorio.pogecommerce.dto.ItemCompraDTO;
import com.emanuelhonorio.pogecommerce.error.exceptions.OutOfStockException;
import com.emanuelhonorio.pogecommerce.error.exceptions.ResourceNotFoundException;
import com.emanuelhonorio.pogecommerce.model.Compra;
import com.emanuelhonorio.pogecommerce.model.Cor;
import com.emanuelhonorio.pogecommerce.model.Estoque;
import com.emanuelhonorio.pogecommerce.model.ItemCompra;
import com.emanuelhonorio.pogecommerce.model.Produto;
import com.emanuelhonorio.pogecommerce.model.Tamanho;
import com.emanuelhonorio.pogecommerce.repository.CompraRepository;
import com.emanuelhonorio.pogecommerce.repository.CorRepository;
import com.emanuelhonorio.pogecommerce.repository.EstoqueRepository;
import com.emanuelhonorio.pogecommerce.repository.TamanhoRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CorRepository corRepository;

	@Autowired
	private TamanhoRepository tamanhoRepository;

	private Cor findCorByIdOrThrow(Long id) {
		return corRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cor not found with id" + id));
	}

	private Tamanho findTamanhoByIdOrThrow(Long id) {
		return tamanhoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tamanho not found with id" + id));
	}

	public Compra comprar(CompraDTO compraDTO) {
		Compra compra = new Compra();

		Set<ItemCompraDTO> itens = compraDTO.getItens();

		Estoque estoque = new Estoque();
		
		for (ItemCompraDTO item : itens) {
			Produto produto = produtoService.findByIdOrThrow(item.getProdutoId());
			Cor cor = findCorByIdOrThrow(item.getCorId());
			Tamanho tamanho = findTamanhoByIdOrThrow(item.getTamanhoId());
			estoque = estoqueRepository.findByCorAndTamanho(cor, tamanho)
					.orElseThrow(() -> new ResourceNotFoundException("Produto not found in stock"));

			// check if stock is available
			if (estoque.getQtdEstoque() <= 0) {
				throw new OutOfStockException("Out of stock for product with id " + produto.getId() + ", color "
						+ cor.getId() + " and tamanho " + tamanho.getId());
			}

			if (item.getQuantidade() > estoque.getQtdEstoque()) {
				throw new OutOfStockException("Only " + estoque.getQtdEstoque() + " available in stock for this item");
			}
			
			// decrease stock
			estoque.setQtdEstoque(estoque.getQtdEstoque() - item.getQuantidade());

			ItemCompra newItem = new ItemCompra();
			newItem.setCompra(compra);
			newItem.setProduto(produto);
			newItem.setNomeCor(cor.getNome());
			newItem.setNomeTamanho(tamanho.getNome());
			newItem.setQuantidade(item.getQuantidade());

			// subtotal is valorDoProduto * quantidade
			// valorDoProduto is produto.valorBase + estoque.acrescimoValor
			BigDecimal valorDoProduto = produto.getValorBase().add(estoque.getAcrescimoValor());
			BigDecimal subTotal = valorDoProduto.multiply(new BigDecimal(item.getQuantidade()));
			newItem.setSubtotal(subTotal);
			
			// Inc total
			BigDecimal total = compra.getTotal() == null ? new BigDecimal(0) : compra.getTotal();
			compra.setTotal(total.add(subTotal));

			compra.getItems().add(newItem);
		}
		
		compra = compraRepository.save(compra);
		
		if (estoque.getId() == null) {
			throw new ResourceNotFoundException("Unexpected stock exception");
		}
		
		estoqueRepository.save(estoque);
		
		return compra;
	}
}
