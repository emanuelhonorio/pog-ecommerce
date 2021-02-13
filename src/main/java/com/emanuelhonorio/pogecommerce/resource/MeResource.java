package com.emanuelhonorio.pogecommerce.resource;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.pogecommerce.dto.CompraDTO;
import com.emanuelhonorio.pogecommerce.model.Compra;
import com.emanuelhonorio.pogecommerce.model.Usuario;
import com.emanuelhonorio.pogecommerce.repository.UsuarioRepository;
import com.emanuelhonorio.pogecommerce.service.CompraService;
import com.emanuelhonorio.pogecommerce.service.filter.CompraFilter;

@RestController
@RequestMapping("/me")
public class MeResource {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CompraService compraService;

	@GetMapping
	public Usuario getMe(Principal principal) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(principal.getName());
		return usuarioOpt.get();
	}

	@GetMapping("/compras")
	public Page<Compra> listar(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "500") int size, CompraFilter compraFilter,
			Principal principal) {

		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(principal.getName());
		return compraService.filtrar(usuarioOpt.get(), compraFilter, page, size);
	}

	@PostMapping("/compras")
	public Compra comprar(@RequestBody @Valid CompraDTO compraDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(currentPrincipalName);

		return compraService.comprar(usuarioOpt.get(), compraDTO, false);
	}

}
