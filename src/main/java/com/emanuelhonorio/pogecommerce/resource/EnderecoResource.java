package com.emanuelhonorio.pogecommerce.resource;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.pogecommerce.dto.EnderecoDTO;
import com.emanuelhonorio.pogecommerce.model.Endereco;
import com.emanuelhonorio.pogecommerce.model.Usuario;
import com.emanuelhonorio.pogecommerce.repository.EnderecoRepository;
import com.emanuelhonorio.pogecommerce.repository.UsuarioRepository;
import com.emanuelhonorio.pogecommerce.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Endereco> listar(Principal principal) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(principal.getName());
		return enderecoRepository.findByUsuario(usuarioOpt.get());
	}

	@PostMapping
	public Endereco adicionar(@RequestBody @Valid EnderecoDTO enderecoDTO, Principal principal) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(principal.getName());
		enderecoDTO.setId(null); // prevents updating
		return enderecoService.save(usuarioOpt.get(), enderecoDTO);
	}

	@PutMapping("/{id}")
	public Endereco adicionar(@PathVariable Long id, @RequestBody @Valid EnderecoDTO enderecoDTO, Principal principal) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(principal.getName());
		enderecoDTO.setId(id);
		return enderecoService.save(usuarioOpt.get(), enderecoDTO);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id, Principal principal) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(principal.getName());
		enderecoService.delete(usuarioOpt.get(), id);
	}
}
