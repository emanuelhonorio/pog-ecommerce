package com.emanuelhonorio.pogecommerce.service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emanuelhonorio.pogecommerce.dto.CompraDTO;
import com.emanuelhonorio.pogecommerce.dto.ItemCompraDTO;
import com.emanuelhonorio.pogecommerce.error.exceptions.OutOfStockException;
import com.emanuelhonorio.pogecommerce.error.exceptions.ResourceNotFoundException;
import com.emanuelhonorio.pogecommerce.model.Compra;
import com.emanuelhonorio.pogecommerce.model.Cor;
import com.emanuelhonorio.pogecommerce.model.Endereco;
import com.emanuelhonorio.pogecommerce.model.Estoque;
import com.emanuelhonorio.pogecommerce.model.ItemCompra;
import com.emanuelhonorio.pogecommerce.model.Produto;
import com.emanuelhonorio.pogecommerce.model.QCompra;
import com.emanuelhonorio.pogecommerce.model.Tamanho;
import com.emanuelhonorio.pogecommerce.model.Usuario;
import com.emanuelhonorio.pogecommerce.model.enums.StatusCompra;
import com.emanuelhonorio.pogecommerce.repository.CompraRepository;
import com.emanuelhonorio.pogecommerce.repository.CorRepository;
import com.emanuelhonorio.pogecommerce.repository.EstoqueRepository;
import com.emanuelhonorio.pogecommerce.repository.TamanhoRepository;
import com.emanuelhonorio.pogecommerce.service.filter.CompraFilter;
import com.querydsl.core.BooleanBuilder;

@Transactional
@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private CorRepository corRepository;

	@Autowired
	private TamanhoRepository tamanhoRepository;

	public Compra findByIdOrThrow(Long id) {
		return this.compraRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tamanho not found with id" + id));
	}

	public Page<Compra> filtrar(Usuario usuario, CompraFilter compraFilter, int page, int size) {
		BooleanBuilder booleanBuilder = this.getBooleanBuilder(compraFilter, usuario);

		return compraRepository.findAll(booleanBuilder, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
	}

	public Page<Compra> filtrarAdmin(CompraFilter compraFilter, int page, int size) {
		BooleanBuilder booleanBuilder = this.getBooleanBuilder(compraFilter, null);

		return compraRepository.findAll(booleanBuilder, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
	}

	public Compra comprar(Usuario usuario, CompraDTO compraDTO) {
		Compra compra = new Compra();

		Endereco endereco = enderecoService.findByIdOrThrow(compraDTO.getEnderecoId());

		compra.setUsuario(usuario);
		compra.setEnderecoDeEntrega(endereco);

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

		// update stock quantity
		estoqueRepository.save(estoque);

		return compra;
	}

	public Compra atualizarStatus(Long id, StatusCompra status) {
		Compra compra = this.findByIdOrThrow(id);
		compra.setStatus(status);

		return compraRepository.save(compra);
	}

	public void safeDelete(Long id) {
		Compra compra = this.findByIdOrThrow(id);
		compra.setDeleted(true);

		compraRepository.save(compra);
	}

	private Cor findCorByIdOrThrow(Long id) {
		return corRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cor not found with id" + id));
	}

	private Tamanho findTamanhoByIdOrThrow(Long id) {
		return tamanhoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tamanho not found with id" + id));
	}

	private BooleanBuilder getBooleanBuilder(CompraFilter compraFilter, Usuario usuario) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (usuario != null) {
			booleanBuilder.and(QCompra.compra.usuario.eq(usuario));
		}

		if (compraFilter.getId() != null) {
			booleanBuilder.or(QCompra.compra.id.eq(compraFilter.getId()));
		}

		if (compraFilter.getEntregue() != null) {
			if (compraFilter.getEntregue()) {
				booleanBuilder.and(QCompra.compra.status.eq(StatusCompra.DELIVERED));				
			} else {
				booleanBuilder.and(QCompra.compra.status.ne(StatusCompra.DELIVERED));
			}
			
		} 
		
		if (compraFilter.getStatus() != null) {
			booleanBuilder.and(QCompra.compra.status.eq(compraFilter.getStatus()));
		}

		if (compraFilter.getData() != null) {
			booleanBuilder.and(QCompra.compra.createdAt.between(compraFilter.getData().atStartOfDay(),
					compraFilter.getData().atTime(LocalTime.MAX)));
		}

		if (compraFilter.getDeleted() != null) {
			booleanBuilder.and(QCompra.compra.deleted.eq(compraFilter.getDeleted()));
		}

		return booleanBuilder;
	}
}
