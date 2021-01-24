package com.emanuelhonorio.pogecommerce.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.pogecommerce.dto.CompraDTO;
import com.emanuelhonorio.pogecommerce.model.Compra;
import com.emanuelhonorio.pogecommerce.model.Usuario;
import com.emanuelhonorio.pogecommerce.repository.CompraRepository;
import com.emanuelhonorio.pogecommerce.repository.UsuarioRepository;
import com.emanuelhonorio.pogecommerce.service.CompraService;

@RestController
@RequestMapping("/compras")
public class CompraResource {

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private CompraService compraService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Compra> listar() {
		return compraRepository.findAll();
	}

	@PostMapping
	public Compra comprar(@RequestBody @Valid CompraDTO compraDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(currentPrincipalName);

		return compraService.comprar(usuarioOpt.get(), compraDTO);
	}
}
