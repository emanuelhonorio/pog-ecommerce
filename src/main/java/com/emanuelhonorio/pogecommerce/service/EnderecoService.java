package com.emanuelhonorio.pogecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.emanuelhonorio.pogecommerce.dto.EnderecoDTO;
import com.emanuelhonorio.pogecommerce.error.exceptions.ResourceNotFoundException;
import com.emanuelhonorio.pogecommerce.error.exceptions.ResourceOwnerException;
import com.emanuelhonorio.pogecommerce.model.Endereco;
import com.emanuelhonorio.pogecommerce.model.Usuario;
import com.emanuelhonorio.pogecommerce.repository.EnderecoRepository;

@Transactional
@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco findByIdOrThrow(Long id) {
		return enderecoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("endereco not found with id " + id));
	}

	public Endereco save(Usuario usuario, EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();

		// he wants to update
		if (endereco.getId() != null) {
			endereco = this.findByIdOrThrow(endereco.getId());

			checkIfAdressBelongsToUserOrThrow(endereco, usuario);
		}

		endereco.setId(enderecoDTO.getId());
		endereco.setCep(enderecoDTO.getCep());
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setNumero(enderecoDTO.getNumero());
		endereco.setUsuario(usuario);

		return enderecoRepository.save(endereco);
	}

	public void delete(Usuario usuario, Long enderecoId) {
		Endereco endereco = findByIdOrThrow(enderecoId);

		checkIfAdressBelongsToUserOrThrow(endereco, usuario);

		enderecoRepository.delete(endereco);
	}

	public void checkIfAdressBelongsToUserOrThrow(Endereco endereco, Usuario usuario) {
		Assert.notNull(endereco.getUsuario().getId());
		if (endereco.getUsuario().getId() != usuario.getId()) {
			throw new ResourceOwnerException("address doesn't belongs to user with id " + usuario.getId());
		}
	}
}
